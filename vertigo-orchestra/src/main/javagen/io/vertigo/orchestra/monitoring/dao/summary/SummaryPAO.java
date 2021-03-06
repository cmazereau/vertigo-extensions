package io.vertigo.orchestra.monitoring.dao.summary;

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
public final class SummaryPAO implements StoreServices {
	private final TaskManager taskManager;

	/**
	 * Constructeur.
	 * @param taskManager Manager des Task
	 */
	@Inject
	public SummaryPAO(final TaskManager taskManager) {
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
	 * Execute la tache TkGetExecutionSummariesByDate.
	 * @param dateMin Instant 
	 * @param dateMax Instant 
	 * @param status String 
	 * @return DtList de OExecutionSummary dtcExecutionSummary
	*/
	public io.vertigo.dynamo.domain.model.DtList<io.vertigo.orchestra.monitoring.domain.summary.OExecutionSummary> getExecutionSummariesByDate(final java.time.Instant dateMin, final java.time.Instant dateMax, final String status) {
		final Task task = createTaskBuilder("TkGetExecutionSummariesByDate")
				.addValue("dateMin", dateMin)
				.addValue("dateMax", dateMax)
				.addValue("status", status)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	/**
	 * Execute la tache TkGetExecutionSummaryByDateAndName.
	 * @param dateMin Instant 
	 * @param dateMax Instant 
	 * @param name String 
	 * @return OExecutionSummary dtExecutionSummary
	*/
	public io.vertigo.orchestra.monitoring.domain.summary.OExecutionSummary getExecutionSummaryByDateAndName(final java.time.Instant dateMin, final java.time.Instant dateMax, final String name) {
		final Task task = createTaskBuilder("TkGetExecutionSummaryByDateAndName")
				.addValue("dateMin", dateMin)
				.addValue("dateMax", dateMax)
				.addValue("name", name)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	private TaskManager getTaskManager() {
		return taskManager;
	}
}
