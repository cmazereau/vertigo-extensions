/**
 * vertigo - simple java starter
 *
 * Copyright (C) 2013-2019, Vertigo.io, KleeGroup, direction.technique@kleegroup.com (http://www.kleegroup.com)
 * KleeGroup, Centre d'affaire la Boursidiere - BP 159 - 92357 Le Plessis Robinson Cedex - France
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.vertigo.datastore.plugins.entitystore.sql;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import io.vertigo.core.lang.Assertion;
import io.vertigo.core.lang.Cardinality;
import io.vertigo.core.lang.Tuple;
import io.vertigo.core.lang.VSystemException;
import io.vertigo.core.node.Home;
import io.vertigo.core.node.definition.DefinitionUtil;
import io.vertigo.core.param.ParamValue;
import io.vertigo.core.util.StringUtil;
import io.vertigo.database.sql.SqlDataBaseManager;
import io.vertigo.database.sql.vendor.SqlDialect;
import io.vertigo.datamodel.criteria.Criteria;
import io.vertigo.datamodel.criteria.CriteriaCtx;
import io.vertigo.datamodel.criteria.CriteriaEncoder;
import io.vertigo.datamodel.criteria.Criterions;
import io.vertigo.datamodel.smarttype.SmartTypeDefinition;
import io.vertigo.datamodel.structure.metamodel.DataType;
import io.vertigo.datamodel.structure.metamodel.DtDefinition;
import io.vertigo.datamodel.structure.metamodel.DtField;
import io.vertigo.datamodel.structure.metamodel.association.AssociationNNDefinition;
import io.vertigo.datamodel.structure.metamodel.association.AssociationNode;
import io.vertigo.datamodel.structure.metamodel.association.DtListURIForNNAssociation;
import io.vertigo.datamodel.structure.metamodel.association.DtListURIForSimpleAssociation;
import io.vertigo.datamodel.structure.model.DtList;
import io.vertigo.datamodel.structure.model.DtListState;
import io.vertigo.datamodel.structure.model.Entity;
import io.vertigo.datamodel.structure.model.UID;
import io.vertigo.datamodel.structure.util.AssociationUtil;
import io.vertigo.datamodel.structure.util.DtObjectUtil;
import io.vertigo.datamodel.task.TaskManager;
import io.vertigo.datamodel.task.metamodel.TaskDefinition;
import io.vertigo.datamodel.task.metamodel.TaskDefinitionBuilder;
import io.vertigo.datamodel.task.model.Task;
import io.vertigo.datamodel.task.model.TaskBuilder;
import io.vertigo.datamodel.task.model.TaskEngine;
import io.vertigo.datastore.entitystore.EntityStoreManager;
import io.vertigo.datastore.impl.entitystore.DataStorePlugin;
import io.vertigo.dynamox.task.AbstractTaskEngineSQL;
import io.vertigo.dynamox.task.TaskEngineProc;
import io.vertigo.dynamox.task.TaskEngineSelect;
import io.vertigo.dynamox.task.sqlserver.TaskEngineInsertWithGeneratedKeys;

/**
 * This class is the basic implementation of the dataStore in the sql way.
 *
 * @author pchretien
 */
public final class SqlEntityStorePlugin implements DataStorePlugin {
	private static final int MAX_TASK_SPECIFIC_NAME_LENGTH = 40;
	private static final String SMART_TYPE_PREFIX = DefinitionUtil.getPrefix(SmartTypeDefinition.class);

	private final String dataSpace;
	private final String connectionName;
	private final String sequencePrefix;
	/**
	 * Domaine à usage interne.
	 * Ce domaine n'est pas enregistré.
	 */
	private final SmartTypeDefinition integerSmartType;

	private enum TASK {
		/** Prefix of the SELECT.*/
		TkSelect,
		/** Prefix of the INSERT.*/
		TkInsert,
		/** Prefix of the UPDATE.*/
		TkUpdate,
		/** Prefix of the DELETE.*/
		TkDelete,
		/** Prefix of the COUNT.*/
		TkCount,
		/** Prefix of the LOCK.*/
		TkLock
	}

	private final SqlDialect sqlDialect;
	private final TaskManager taskManager;
	private final CriteriaEncoder criteriaEncoder;

	/**
	 * Constructor.
	 * @param optDataSpace the dataSpace (option)
	 * @param optConnectionName the name of the connection
	 * @param optSequencePrefix the prefix of sequences
	 * @param taskManager the taskManager
	 * @param sqlDataBaseManager the sqlDataBaseManager
	 */
	@Inject
	public SqlEntityStorePlugin(
			@ParamValue("dataSpace") final Optional<String> optDataSpace,
			@ParamValue("connectionName") final Optional<String> optConnectionName,
			@ParamValue("sequencePrefix") final Optional<String> optSequencePrefix,
			final TaskManager taskManager,
			final SqlDataBaseManager sqlDataBaseManager) {
		Assertion.checkNotNull(optDataSpace);
		Assertion.checkNotNull(optConnectionName);
		Assertion.checkNotNull(optSequencePrefix);
		Assertion.checkNotNull(taskManager);
		Assertion.checkNotNull(sqlDataBaseManager);
		//-----
		dataSpace = optDataSpace.orElse(EntityStoreManager.MAIN_DATA_SPACE_NAME);
		connectionName = optConnectionName.orElse(SqlDataBaseManager.MAIN_CONNECTION_PROVIDER_NAME);
		sequencePrefix = optSequencePrefix.orElse("SEQ_");
		this.taskManager = taskManager;
		sqlDialect = sqlDataBaseManager.getConnectionProvider(connectionName).getDataBase().getSqlDialect();
		criteriaEncoder = new SqlCriteriaEncoder(sqlDialect);
		integerSmartType = SmartTypeDefinition.builder("STyIntegerSql", DataType.Integer).build();
	}

	/**
	 * Return the tableName to which the dtDefinition is mapped.
	 *
	 * @param dtDefinition the dtDefinition
	 * @return the name of the table
	 */
	private static String getEntityName(final DtDefinition dtDefinition) {
		return dtDefinition.getFragment().orElse(dtDefinition).getLocalName();
	}

	private static String getRequestedCols(final DtDefinition dtDefinition) {
		if (dtDefinition.getFragment().isPresent()) {
			return dtDefinition.getFields()
					.stream()
					.map(DtField::getName)
					.map(StringUtil::camelToConstCase)
					.collect(Collectors.joining(", "));
		}
		return "*"; //all fields
	}

	/** {@inheritDoc} */
	@Override
	public String getDataSpace() {
		return dataSpace;
	}

	/** {@inheritDoc} */
	@Override
	public String getConnectionName() {
		return connectionName;
	}

	private static DtField getIdField(final DtDefinition dtDefinition) {
		Assertion.checkNotNull(dtDefinition);
		//---
		return dtDefinition.getIdField().orElseThrow(() -> new IllegalStateException("no ID found"));
	}

	/** {@inheritDoc} */
	@Override
	public <E extends Entity> E readNullable(final DtDefinition dtDefinition, final UID<E> uri) {
		final String entityName = getEntityName(dtDefinition);
		final String tableName = StringUtil.camelToConstCase(entityName);
		final String taskName = TASK.TkSelect + entityName + "ByUri";

		final String requestedCols = getRequestedCols(dtDefinition);
		final DtField idField = getIdField(dtDefinition);
		final String idFieldName = idField.getName();
		final String request = new StringBuilder()
				.append(" select ").append(requestedCols)
				.append(" from ").append(tableName)
				.append(" where ").append(StringUtil.camelToConstCase(idFieldName)).append(" = #").append(idFieldName).append('#')
				.toString();

		final TaskDefinition taskDefinition = TaskDefinition.builder(taskName)
				.withEngine(TaskEngineSelect.class)
				.withDataSpace(dataSpace)
				.withRequest(request)
				.addInAttribute(idFieldName, idField.getDomain(), Cardinality.ONE)
				.withOutAttribute("dto", Home.getApp().getDefinitionSpace().resolve(SMART_TYPE_PREFIX + uri.getDefinition().getName(), SmartTypeDefinition.class), Cardinality.OPTIONAL_OR_NULLABLE)
				.build();

		final Task task = Task.builder(taskDefinition)
				.addValue(idFieldName, uri.getId())
				.build();

		return taskManager
				.execute(task)
				.getResult();
	}

	/** {@inheritDoc} */
	@Override
	public <E extends Entity> DtList<E> findAll(final DtDefinition dtDefinition, final DtListURIForNNAssociation dtcUri) {
		Assertion.checkNotNull(dtDefinition);
		Assertion.checkNotNull(dtcUri);
		//-----
		final String entityName = getEntityName(dtDefinition);
		final String tableName = StringUtil.camelToConstCase(entityName);

		final String taskName = TASK.TkSelect + "NNList" + entityName + "ByUri";

		//PK de la DtList recherchée
		final String idFieldName = StringUtil.camelToConstCase(getIdField(dtDefinition).getName());
		//FK dans la table nn correspondant à la collection recherchée. (clé de jointure ).
		final AssociationNNDefinition associationNNDefinition = dtcUri.getAssociationDefinition();
		final String joinTableName = associationNNDefinition.getTableName();
		final DtDefinition joinDtDefinition = AssociationUtil.getAssociationNode(associationNNDefinition, dtcUri.getRoleName()).getDtDefinition();
		final String joinDtFieldName = StringUtil.camelToConstCase(getIdField(joinDtDefinition).getName());

		//La condition s'applique sur l'autre noeud de la relation (par rapport à la collection attendue)
		final AssociationNode associationNode = AssociationUtil.getAssociationNodeTarget(associationNNDefinition, dtcUri.getRoleName());
		final DtField fkField = getIdField(associationNode.getDtDefinition());
		final String fkFieldName = fkField.getName();

		final String request = new StringBuilder(" select t.* from ")
				.append(tableName).append(" t")
				//On établit une jointure fermée entre la pk et la fk de la collection recherchée.
				.append(" join ").append(joinTableName).append(" j on j.").append(joinDtFieldName).append(" = t.").append(idFieldName)
				//Condition de la recherche
				.append(" where j.").append(StringUtil.camelToConstCase(fkFieldName)).append(" = #").append(fkFieldName).append('#')
				.toString();

		final TaskDefinition taskDefinition = TaskDefinition.builder(taskName)
				.withEngine(TaskEngineSelect.class)
				.withDataSpace(dataSpace)
				.withRequest(request)
				.addInAttribute(fkFieldName, fkField.getDomain(), Cardinality.ONE)
				.withOutAttribute("dtc", Home.getApp().getDefinitionSpace().resolve(SMART_TYPE_PREFIX + dtDefinition.getName(), SmartTypeDefinition.class), Cardinality.MANY)
				.build();

		final UID uid = dtcUri.getSource();

		final Task task = Task.builder(taskDefinition)
				.addValue(fkFieldName, uid.getId())
				.build();

		return taskManager
				.execute(task)
				.getResult();
	}

	/** {@inheritDoc} */
	@Override
	public <E extends Entity> DtList<E> findAll(final DtDefinition dtDefinition, final DtListURIForSimpleAssociation dtcUri) {
		Assertion.checkNotNull(dtDefinition);
		Assertion.checkNotNull(dtcUri);
		//---
		final DtField fkField = dtcUri.getAssociationDefinition().getFKField();
		final Serializable value = dtcUri.getSource().getId();

		return findByCriteria(dtDefinition, Criterions.isEqualTo(fkField::getName, value), DtListState.of(null));
	}

	/** {@inheritDoc} */
	@Override
	public <E extends Entity> DtList<E> findByCriteria(final DtDefinition dtDefinition, final Criteria<E> criteria, final DtListState dtListState) {
		Assertion.checkNotNull(dtDefinition);
		Assertion.checkNotNull(criteria);
		Assertion.checkNotNull(dtListState);
		//---
		final String entityName = getEntityName(dtDefinition);
		final String tableName = StringUtil.camelToConstCase(entityName);
		final String requestedCols = getRequestedCols(dtDefinition);
		final String taskName = getListTaskName(entityName);
		final Tuple<String, CriteriaCtx> tuple = criteria.toStringAnCtx(criteriaEncoder);
		final String where = tuple.getVal1();
		final String request = createLoadAllLikeQuery(tableName, requestedCols, where, dtListState);
		final TaskDefinitionBuilder taskDefinitionBuilder = TaskDefinition.builder(taskName)
				.withEngine(TaskEngineSelect.class)
				.withDataSpace(dataSpace)
				.withRequest(request);

		final CriteriaCtx ctx = tuple.getVal2();
		//IN, Optional
		for (final String attributeName : ctx.getAttributeNames()) {
			taskDefinitionBuilder.addInAttribute(attributeName, dtDefinition.getField(ctx.getDtFieldName(attributeName)).getDomain(), Cardinality.OPTIONAL_OR_NULLABLE);
		}
		//OUT, obligatoire
		final TaskDefinition taskDefinition = taskDefinitionBuilder
				.withOutAttribute("dtc", Home.getApp().getDefinitionSpace().resolve(SMART_TYPE_PREFIX + dtDefinition.getName(), SmartTypeDefinition.class), Cardinality.MANY)
				.build();

		final TaskBuilder taskBuilder = Task.builder(taskDefinition);
		for (final String attributeName : ctx.getAttributeNames()) {
			taskBuilder.addValue(attributeName, ctx.getAttributeValue(attributeName));
		}
		return taskManager
				.execute(taskBuilder.build())
				.getResult();
	}

	private static String getListTaskName(final String entityName) {
		final String fullName = new StringBuilder(TASK.TkSelect.name())
				.append("List")
				.append(entityName)
				.append("ByCriteria")
				.toString();
		if (fullName.length() > MAX_TASK_SPECIFIC_NAME_LENGTH) {
			return fullName.substring(0, MAX_TASK_SPECIFIC_NAME_LENGTH);
		}
		return fullName;
	}

	//==========================================================================
	//=============================== Ecriture =================================
	//==========================================================================
	/** {@inheritDoc} */
	@Override
	public <E extends Entity> E create(final DtDefinition dtDefinition, final E entity) {
		Assertion.checkArgument(DtObjectUtil.getId(entity) == null, "Only object without any id can be created");
		//------
		final boolean insert = true;
		put(entity, insert);
		return entity;
	}

	/** {@inheritDoc} */
	@Override
	public void update(final DtDefinition dtDefinition, final Entity entity) {
		Assertion.checkNotNull(DtObjectUtil.getId(entity), "Need an id to update an object ");
		//-----
		final boolean insert = false;
		put(entity, insert);
	}

	/**
	 * Creates the update request.
	 *
	 * @param dtDefinition the dtDefinition
	 * @return the sql request
	 */
	private static String createUpdateQuery(final DtDefinition dtDefinition) {
		final String entityName = getEntityName(dtDefinition);
		final String tableName = StringUtil.camelToConstCase(entityName);
		final DtField idField = getIdField(dtDefinition);

		return new StringBuilder()
				.append("update ").append(tableName).append(" set ")

				.append(dtDefinition.getFields()
						.stream()
						.filter(dtField -> dtField.isPersistent() && !dtField.getType().isId())
						.map(dtField -> StringUtil.camelToConstCase(dtField.getName()) + " =#dto." + dtField.getName() + '#')
						.collect(Collectors.joining(", ")))
				.append(" where ")
				.append(StringUtil.camelToConstCase(idField.getName())).append(" = #dto.").append(idField.getName()).append('#')
				.toString();
	}

	/**
	 * @param insert Si opération de type insert
	 * @return Classe du moteur de tache à utiliser
	 */
	private static Class<? extends TaskEngine> getTaskEngineClass(final boolean insert) {
		if (insert) {
			return TaskEngineInsertWithGeneratedKeys.class;
		}
		return TaskEngineProc.class;
	}

	/**
	 * @param entity Objet à persiter
	 * @param insert Si opération de type insert (update sinon)
	 */
	private void put(final Entity entity, final boolean insert) {
		final DtDefinition dtDefinition = DtObjectUtil.findDtDefinition(entity);
		final String entityName = getEntityName(dtDefinition);
		final String tableName = StringUtil.camelToConstCase(entityName);
		final String taskName = (insert ? TASK.TkInsert : TASK.TkUpdate) + entityName;

		final String request = insert ? sqlDialect.createInsertQuery(dtDefinition.getIdField().get().getName(), getDataFields(dtDefinition), sequencePrefix, tableName) : createUpdateQuery(dtDefinition);

		final TaskDefinition taskDefinition = TaskDefinition.builder(taskName)
				.withEngine(getTaskEngineClass(insert))
				.withDataSpace(dataSpace)
				.withRequest(request)
				.addInAttribute("dto", Home.getApp().getDefinitionSpace().resolve(SMART_TYPE_PREFIX + dtDefinition.getName(), SmartTypeDefinition.class), Cardinality.ONE)
				.withOutAttribute(AbstractTaskEngineSQL.SQL_ROWCOUNT, integerSmartType, Cardinality.ONE)
				.build();

		final Task task = Task.builder(taskDefinition)
				.addValue("dto", entity)
				.build();

		final int sqlRowCount = taskManager
				.execute(task)
				.getResult();

		if (sqlRowCount > 1) {
			throw new VSystemException("more than one row has been " + (insert ? "created" : "updated"));
		}
		if (sqlRowCount == 0) {
			throw new VSystemException("no data " + (insert ? "created" : "updated"));
		}
	}

	private static List<String> getDataFields(final DtDefinition dtDefinition) {
		return dtDefinition.getFields()
				.stream()
				.filter(dtField -> !dtField.getType().isId())
				.filter(DtField::isPersistent)
				.map(DtField::getName)
				.collect(Collectors.toList());
	}

	/** {@inheritDoc} */
	@Override
	public void delete(final DtDefinition dtDefinition, final UID uri) {
		Assertion.checkNotNull(dtDefinition);
		Assertion.checkNotNull(uri);
		//---
		final DtField idField = getIdField(dtDefinition);
		final String entityName = getEntityName(dtDefinition);
		final String tableName = StringUtil.camelToConstCase(entityName);
		final String taskName = TASK.TkDelete + entityName;

		final String idFieldName = idField.getName();

		final String request = new StringBuilder()
				.append("delete from ").append(tableName)
				.append(" where ").append(StringUtil.camelToConstCase(idFieldName)).append(" = #").append(idFieldName).append('#')
				.toString();

		final TaskDefinition taskDefinition = TaskDefinition.builder(taskName)
				.withEngine(TaskEngineProc.class)
				.withDataSpace(dataSpace)
				.withRequest(request)
				.addInAttribute(idFieldName, idField.getDomain(), Cardinality.ONE)
				.withOutAttribute(AbstractTaskEngineSQL.SQL_ROWCOUNT, integerSmartType, Cardinality.ONE)
				.build();

		final Task task = Task.builder(taskDefinition)
				.addValue(idFieldName, uri.getId())
				.build();

		final int sqlRowCount = taskManager
				.execute(task)
				.getResult();

		if (sqlRowCount > 1) {
			throw new VSystemException("more than one row has been deleted");
		} else if (sqlRowCount == 0) {
			throw new VSystemException("no row has been deleted");
		}
	}

	/** {@inheritDoc} */
	@Override
	public int count(final DtDefinition dtDefinition) {
		Assertion.checkNotNull(dtDefinition);
		Assertion.checkArgument(dtDefinition.isPersistent(), "DtDefinition is not  persistent");
		//-----
		final String entityName = getEntityName(dtDefinition);
		final String tableName = StringUtil.camelToConstCase(entityName);
		final String taskName = TASK.TkCount + entityName;
		final SmartTypeDefinition countSmartType = SmartTypeDefinition.builder("STyCount", DataType.Long).build();

		final String request = "select count(*) from " + tableName;

		final TaskDefinition taskDefinition = TaskDefinition.builder(taskName)
				.withEngine(TaskEngineSelect.class)
				.withDataSpace(dataSpace)
				.withRequest(request)
				.withOutAttribute("count", countSmartType, Cardinality.ONE)
				.build();

		final Task task = Task.builder(taskDefinition)
				.build();

		final Long count = taskManager
				.execute(task)
				.getResult();

		return count.intValue();
	}

	/** {@inheritDoc} */
	@Override
	public <E extends Entity> E readNullableForUpdate(final DtDefinition dtDefinition, final UID<?> uri) {
		final String entityName = getEntityName(dtDefinition);
		final String tableName = StringUtil.camelToConstCase(entityName);
		final String taskName = TASK.TkLock + entityName;

		final String requestedCols = getRequestedCols(dtDefinition);
		final DtField idField = getIdField(dtDefinition);
		final String idFieldName = idField.getName();
		final String request = sqlDialect.createSelectForUpdateQuery(tableName, requestedCols, idFieldName);

		final TaskDefinition taskDefinition = TaskDefinition.builder(taskName)
				.withEngine(TaskEngineSelect.class)
				.withDataSpace(dataSpace)
				.withRequest(request)
				.addInAttribute(idFieldName, idField.getDomain(), Cardinality.ONE)
				.withOutAttribute("dto", Home.getApp().getDefinitionSpace().resolve(SMART_TYPE_PREFIX + uri.getDefinition().getName(), SmartTypeDefinition.class), Cardinality.OPTIONAL_OR_NULLABLE)
				.build();

		final Task task = Task.builder(taskDefinition)
				.addValue(idFieldName, uri.getId())
				.build();

		return taskManager
				.execute(task)
				.getResult();
	}

	private String createLoadAllLikeQuery(
			final String tableName,
			final String requestedFields,
			final String where,
			final DtListState dtListState) {

		final StringBuilder request = new StringBuilder("select ")
				.append(requestedFields)
				.append(" from ").append(tableName)
				.append(" where ").append(where);

		sqlDialect.appendListState(request,
				dtListState.getMaxRows().orElse(null),
				dtListState.getSkipRows(),
				dtListState.getSortFieldName().orElse(null),
				dtListState.isSortDesc().orElse(false));
		return request.toString();
	}
}
