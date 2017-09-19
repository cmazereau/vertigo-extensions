/**
 * vertigo - simple java starter
 *
 * Copyright (C) 2013-2017, KleeGroup, direction.technique@kleegroup.com (http://www.kleegroup.com)
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
package io.vertigo.database.plugins.sql.connection.hibernate;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.hibernate.Session;

import io.vertigo.commons.transaction.VTransaction;
import io.vertigo.commons.transaction.VTransactionManager;
import io.vertigo.core.component.Activeable;
import io.vertigo.database.plugins.sql.connection.AbstractSqlConnectionProviderPlugin;
import io.vertigo.database.sql.SqlDataBaseManager;
import io.vertigo.database.sql.connection.SqlConnection;
import io.vertigo.database.sql.vendor.SqlDataBase;
import io.vertigo.lang.Assertion;
import io.vertigo.util.ClassUtil;

/**
 * ConnectionProvider permettant la connexion à une datasource Java.
 *
 * @author pchretien, npiedeloup
 */
public final class HibernateConnectionProviderPlugin extends AbstractSqlConnectionProviderPlugin implements Activeable {
	private final VTransactionManager transactionManager;
	private final String persistenceUnit;

	/**
	 * Constructor.
	 * @param name ConnectionProvider's name
	 * @param dataBaseName Nom du type de base de données
	 * @param persistenceUnit Nom de la persistenceUnit à utiliser (dans le persistence.xml)
	 * @param transactionManager Transaction manager
	 */
	@Inject
	public HibernateConnectionProviderPlugin(
			@Named("name") final Optional<String> name,
			@Named("persistenceUnit") final String persistenceUnit,
			@Named("dataBaseName") final String dataBaseName,
			final VTransactionManager transactionManager) {
		super(name.orElse(SqlDataBaseManager.MAIN_CONNECTION_PROVIDER_NAME), new JpaDataBase(createDataBase(dataBaseName)));
		Assertion.checkArgNotEmpty(persistenceUnit);
		Assertion.checkNotNull(transactionManager);
		//-----
		this.transactionManager = transactionManager;
		this.persistenceUnit = persistenceUnit;
	}

	@Override
	public void start() {
		((JpaDataBase) getDataBase()).setEntityManagerFactory(Persistence.createEntityManagerFactory(persistenceUnit));
	}

	@Override
	public void stop() {
		// nothing
	}

	/**
	 * @param em EntityManager
	 * @return the sqlConnection
	 */
	private SqlConnection obtainWrappedConnection(final EntityManager em) {
		//preconisation StackOverFlow to get current jpa connection
		final Session session = em.unwrap(Session.class);
		return session.doReturningWork(connection -> new SqlConnection(connection, getDataBase(), false));

	}

	/** {@inheritDoc} */
	@Override
	public SqlConnection obtainConnection() {
		final EntityManager em = obtainJpaResource().getEntityManager();
		return obtainWrappedConnection(em);
	}

	/** récupère la ressource JPA de la transaction et la créé si nécessaire. */
	private JpaResource obtainJpaResource() {
		final SqlDataBase dataBase = getDataBase();
		Assertion.checkState(dataBase instanceof JpaDataBase, "DataBase must be a JpaDataBase (current:{0}).", dataBase.getClass());
		return ((JpaDataBase) dataBase).obtainJpaResource(getCurrentTransaction());
	}

	/** récupère la transaction courante. */
	private VTransaction getCurrentTransaction() {
		return transactionManager.getCurrentTransaction();
	}

	private static SqlDataBase createDataBase(final String dataBaseName) {
		return ClassUtil.newInstance(dataBaseName, SqlDataBase.class);
	}

}
