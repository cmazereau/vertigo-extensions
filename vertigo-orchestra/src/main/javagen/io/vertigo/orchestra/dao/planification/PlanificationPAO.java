package io.vertigo.orchestra.dao.planification;

import javax.inject.Inject;

import io.vertigo.app.Home;
import io.vertigo.dynamo.task.TaskManager;
import io.vertigo.dynamo.task.metamodel.TaskDefinition;
import io.vertigo.dynamo.task.model.Task;
import io.vertigo.dynamo.task.model.TaskBuilder;
import io.vertigo.dynamo.store.StoreServices;
import io.vertigo.lang.Assertion;
import io.vertigo.lang.Generated;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
 @Generated
public final class PlanificationPAO implements StoreServices {
	private final TaskManager taskManager;

	/**
	 * Constructeur.
	 * @param taskManager Manager des Task
	 */
	@Inject
	public PlanificationPAO(final TaskManager taskManager) {
		Assertion.checkNotNull(taskManager);
		//-----
		this.taskManager = taskManager;
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
	 * Execute la tache TkCleanFuturePlanifications.
	 * @param processName String 
	*/
	public void cleanFuturePlanifications(final String processName) {
		final Task task = createTaskBuilder("TkCleanFuturePlanifications")
				.addValue("processName", processName)
				.build();
		getTaskManager().execute(task);
	}

	/**
	 * Execute la tache TkCleanPlanificationsOnBoot.
	 * @param currentDate Instant 
	*/
	public void cleanPlanificationsOnBoot(final java.time.Instant currentDate) {
		final Task task = createTaskBuilder("TkCleanPlanificationsOnBoot")
				.addValue("currentDate", currentDate)
				.build();
		getTaskManager().execute(task);
	}

	/**
	 * Execute la tache TkReserveProcessToExecute.
	 * @param lowerLimit Instant 
	 * @param upperLimit Instant 
	 * @param nodId Long 
	*/
	public void reserveProcessToExecute(final java.time.Instant lowerLimit, final java.time.Instant upperLimit, final Long nodId) {
		final Task task = createTaskBuilder("TkReserveProcessToExecute")
				.addValue("lowerLimit", lowerLimit)
				.addValue("upperLimit", upperLimit)
				.addValue("nodId", nodId)
				.build();
		getTaskManager().execute(task);
	}

	private TaskManager getTaskManager() {
		return taskManager;
	}
}
