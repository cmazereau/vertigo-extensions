package io.vertigo.x.rules.dao;

import javax.inject.Inject;

import io.vertigo.app.Home;
import io.vertigo.dynamo.impl.store.util.DAO;
import io.vertigo.dynamo.store.StoreManager;
import io.vertigo.dynamo.store.StoreServices;
import io.vertigo.dynamo.task.TaskManager;
import io.vertigo.dynamo.task.metamodel.TaskDefinition;
import io.vertigo.dynamo.task.model.Task;
import io.vertigo.dynamo.task.model.TaskBuilder;
import io.vertigo.x.rules.domain.SelectorDefinition;

/**
 * DAO : Accès à un object (DTO, DTC). 
 * SelectorDefinitionDAO
 */
public final class SelectorDefinitionDAO extends DAO<SelectorDefinition, java.lang.Long> implements StoreServices {

	/**
	 * Contructeur.
	 * @param storeManager Manager de persistance
	 * @param taskManager Manager de Task
	 */
	@Inject
	public SelectorDefinitionDAO(final StoreManager storeManager, final TaskManager taskManager) {
		super(SelectorDefinition.class, storeManager, taskManager);
	}

	/**
	 * Creates a taskBuilder.
	 * @param name  the name of the task
	 * @return the builder 
	 */
	private static TaskBuilder createTaskBuilder(final String name) {
		final TaskDefinition taskDefinition = Home.getApp().getDefinitionSpace().resolve(name, TaskDefinition.class);
		return Task.builder(taskDefinition);
	}

	/**
	 * Execute la tache TK_FIND_ALL_SELECTORS_BY_WORKFLOW_DEFINITION_ID.
	 * @param wfwdId Long 
	 * @return io.vertigo.dynamo.domain.model.DtList<io.vertigo.x.rules.domain.SelectorDefinition> selectorDefinitionList
	*/
	public io.vertigo.dynamo.domain.model.DtList<io.vertigo.x.rules.domain.SelectorDefinition> findAllSelectorsByWorkflowDefinitionId(final Long wfwdId) {
		final Task task = createTaskBuilder("TK_FIND_ALL_SELECTORS_BY_WORKFLOW_DEFINITION_ID")
				.addValue("WFWD_ID", wfwdId)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

}