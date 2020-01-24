package io.vertigo.struts2.data.dao.people;

import javax.inject.Inject;

import io.vertigo.core.lang.Generated;
import io.vertigo.datastore.entitystore.EntityStoreManager;
import io.vertigo.datastore.impl.dao.DAO;
import io.vertigo.datastore.impl.dao.StoreServices;
import io.vertigo.dynamo.ngdomain.ModelManager;
import io.vertigo.dynamo.task.TaskManager;
import io.vertigo.struts2.data.domain.people.Casting;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
@Generated
public final class CastingDAO extends DAO<Casting, java.lang.Long> implements StoreServices {

	/**
	 * Contructeur.
	 * @param entityStoreManager Manager de persistance
	 * @param taskManager Manager de Task
	 */
	@Inject
	public CastingDAO(final EntityStoreManager entityStoreManager, final TaskManager taskManager, final ModelManager modelManager) {
		super(Casting.class, entityStoreManager, taskManager, modelManager);
	}

}