package io.vertigo.orchestra.dao.schedule;

import javax.inject.Inject;

import io.vertigo.app.Home;
import io.vertigo.dynamo.impl.store.util.DAO;
import io.vertigo.dynamo.store.StoreManager;
import io.vertigo.dynamo.store.StoreServices;
import io.vertigo.dynamo.task.TaskManager;
import io.vertigo.dynamo.task.metamodel.TaskDefinition;
import io.vertigo.dynamo.task.model.Task;
import io.vertigo.dynamo.task.model.TaskBuilder;
import io.vertigo.lang.Generated;
import io.vertigo.orchestra.domain.schedule.OJobSchedule;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
 @Generated
public final class OJobScheduleDAO extends DAO<OJobSchedule, java.lang.Long> implements StoreServices {

	/**
	 * Contructeur.
	 * @param storeManager Manager de persistance
	 * @param taskManager Manager de Task
	 */
	@Inject
	public OJobScheduleDAO(final StoreManager storeManager, final TaskManager taskManager) {
		super(OJobSchedule.class, storeManager, taskManager);
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
	 * Execute la tache TK_GET_JOBS_SCHEDULE_TO_RUN.
	 * @param scheduleDate java.time.ZonedDateTime 
	 * @return io.vertigo.dynamo.domain.model.DtList<io.vertigo.orchestra.domain.schedule.OJobSchedule> dtoOJobSchedule
	*/
	public io.vertigo.dynamo.domain.model.DtList<io.vertigo.orchestra.domain.schedule.OJobSchedule> getJobsScheduleToRun(final java.time.ZonedDateTime scheduleDate) {
		final Task task = createTaskBuilder("TK_GET_JOBS_SCHEDULE_TO_RUN")
				.addValue("SCHEDULE_DATE", scheduleDate)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

}
