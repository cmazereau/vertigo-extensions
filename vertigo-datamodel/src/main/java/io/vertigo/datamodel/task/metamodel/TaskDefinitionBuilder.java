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
package io.vertigo.datamodel.task.metamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.vertigo.core.lang.Assertion;
import io.vertigo.core.lang.Builder;
import io.vertigo.core.lang.Cardinality;
import io.vertigo.datamodel.smarttype.SmartTypeDefinition;
import io.vertigo.datamodel.structure.metamodel.DtDefinition;
import io.vertigo.datamodel.task.model.TaskEngine;

/**
 * Builder of taskDefinition.
 *
 * @author  fconstantin, pchretien
 */
public final class TaskDefinitionBuilder implements Builder<TaskDefinition> {
	private final List<TaskAttribute> myInTaskAttributes = new ArrayList<>();
	private TaskAttribute myOutTaskAttribute;
	private final String myTaskDefinitionName;
	private Class<? extends TaskEngine> myTaskEngineClass;
	private String myRequest;
	private String myPackageName;
	private String myDataSpace;

	/**
	 * Constructor.
	 *
	 * @param taskDefinitionName the name of the taskDefinition (TK_XXX_YYY)
	 */
	TaskDefinitionBuilder(final String taskDefinitionName) {
		Assertion.checkNotNull(taskDefinitionName);
		//-----
		myTaskDefinitionName = taskDefinitionName;
	}

	/**
	 * Defines the engine, used at runtime to process the task.
	 *
	 * @param taskEngineClass Class running the task
	 * @return this builder
	 */
	public TaskDefinitionBuilder withEngine(final Class<? extends TaskEngine> taskEngineClass) {
		Assertion.checkNotNull(taskEngineClass);
		Assertion.checkArgument(TaskEngine.class.isAssignableFrom(taskEngineClass), "class must extends TaskEngine");
		//We have to do this  test because generics are not safe
		//---
		myTaskEngineClass = taskEngineClass;
		return this;
	}

	/**
	 * @param request the request used to configure the task. (ldap request, sql request...)
	 * @return this builder
	 */
	public TaskDefinitionBuilder withRequest(final String request) {
		Assertion.checkNotNull(request);
		//-----
		//Pour unifier la saisie de la request sous un environnement unix ou dos
		// et pour éviter la disparité de gestion des retours chariot
		//par certains drivers de base de données.
		myRequest = request.replace("\r", "");
		return this;
	}

	/**
	 * @param packageName the name of the package
	 * @return this builder
	 */
	public TaskDefinitionBuilder withPackageName(final String packageName) {
		//packageName peut être null
		//-----
		myPackageName = packageName;
		return this;
	}

	/**
	 * Sets the dataSpace
	 * @param dataSpace the dataSpace
	 * @return this builder
	 */
	public TaskDefinitionBuilder withDataSpace(final String dataSpace) {
		//dataSpace can be null
		//-----
		myDataSpace = dataSpace;
		return this;
	}

	/**
	 * Adds an input attribute.
	 *
	 * @param attributeName the name of the attribute
	 * @param domain the domain of the attribute
	 * @param cardinality cadinality (one, optional, many)
	 * @return this builder
	 */
	public TaskDefinitionBuilder addInAttribute(final String attributeName, final SmartTypeDefinition smartTypeDefinition, final Cardinality cardinality) {
		Assertion.checkNotNull(attributeName);
		Assertion.checkNotNull(smartTypeDefinition);
		Assertion.checkNotNull(cardinality);
		//-----
		final TaskAttribute taskAttribute = new TaskAttribute(attributeName, smartTypeDefinition, cardinality);
		myInTaskAttributes.add(taskAttribute);
		return this;
	}

	/**
	 * Adds an output attribute.
	 *
	 * @param attributeName the name of the attribute
	 * @param domain the domain of the attribute
	 * @param cardinality cadinality (one, optional, many)
	 * @return this builder
	 */
	public TaskDefinitionBuilder withOutAttribute(final String attributeName, final SmartTypeDefinition smartTypeDefinition, final Cardinality cardinality) {
		//-----
		myOutTaskAttribute = new TaskAttribute(attributeName, smartTypeDefinition, cardinality);
		return this;
	}

	/** {@inheritDoc} */
	@Override
	public TaskDefinition build() {
		return new TaskDefinition(
				myTaskDefinitionName,
				myPackageName,
				myDataSpace == null ? DtDefinition.DEFAULT_DATA_SPACE : myDataSpace,
				myTaskEngineClass,
				myRequest,
				myInTaskAttributes,
				Optional.ofNullable(myOutTaskAttribute));
	}

}