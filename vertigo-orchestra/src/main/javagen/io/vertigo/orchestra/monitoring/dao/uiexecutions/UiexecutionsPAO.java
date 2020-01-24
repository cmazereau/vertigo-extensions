package io.vertigo.orchestra.monitoring.dao.uiexecutions;

import javax.inject.Inject;

import io.vertigo.core.node.Home;
import io.vertigo.datamodel.task.TaskManager;
import io.vertigo.datamodel.task.metamodel.TaskDefinition;
import io.vertigo.datamodel.task.model.Task;
import io.vertigo.datamodel.task.model.TaskBuilder;
import io.vertigo.core.lang.Assertion;
import io.vertigo.core.lang.Generated;
import io.vertigo.datastore.impl.dao.StoreServices;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
 @Generated
public final class UiexecutionsPAO implements StoreServices {
	private final TaskManager taskManager;

	/**
	 * Constructeur.
	 * @param taskManager Manager des Task
	 */
	@Inject
	public UiexecutionsPAO(final TaskManager taskManager) {
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
	 * Execute la tache StTkGetActivitiesByPreId.
	 * @param preId Long
	 * @return DtList de OActivityExecutionUi dtcOActivityExecutionUi
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			dataSpace = "orchestra",
			name = "TkGetActivitiesByPreId",
			request = "select  ace.ACE_ID as ACE_ID," + 
 "        			act.LABEL as LABEL," + 
 "        			ace.BEGIN_TIME as BEGIN_TIME," + 
 "        			ace.END_TIME as END_TIME," + 
 "        			round(extract('epoch' from (ace.END_TIME-ace.BEGIN_TIME))) as EXECUTION_TIME," + 
 "        			ace.EST_CD as STATUS," + 
 "        			max((case when acw.IS_IN is true then acw.WORKSPACE else null end)) as WORKSPACE_IN," + 
 "        			max((case when acw.IS_IN is false then acw.WORKSPACE else null end)) as WORKSPACE_OUT," + 
 "        			acl.ATTACHMENT is not null as HAS_ATTACHMENT," + 
 "        			acl.LOG is not null as HAS_TECHNICAL_LOG" + 
 "        	from o_activity_execution ace" + 
 "        	join o_activity act on act.ACT_ID = ace.ACT_ID" + 
 "        	join o_activity_workspace acw on acw.ACE_ID = ace.ACE_ID" + 
 "        	left join o_activity_log acl on acl.ACE_ID = ace.ACE_ID" + 
 "        	where ace.PRE_ID = #preId#" + 
 "        	group by ace.ACE_ID, act.LABEL, ace.BEGIN_TIME, ace.END_TIME, acl.ATTACHMENT, acl.LOG",
			taskEngineClass = io.vertigo.dynamox.task.TaskEngineSelect.class)
	@io.vertigo.datamodel.task.proxy.TaskOutput(domain = "STyDtOActivityExecutionUi")
	public io.vertigo.datamodel.structure.model.DtList<io.vertigo.orchestra.monitoring.domain.uiexecutions.OActivityExecutionUi> getActivitiesByPreId(@io.vertigo.datamodel.task.proxy.TaskInput(name = "preId", domain = "STyOIdentifiant") final Long preId) {
		final Task task = createTaskBuilder("TkGetActivitiesByPreId")
				.addValue("preId", preId)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	/**
	 * Execute la tache StTkGetActivitiyByAceId.
	 * @param aceId Long
	 * @return OActivityExecutionUi dtOActivityExecutionUi
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			dataSpace = "orchestra",
			name = "TkGetActivitiyByAceId",
			request = "select  ace.ACE_ID as ACE_ID," + 
 "        			act.LABEL as LABEL," + 
 "        			ace.BEGIN_TIME as BEGIN_TIME," + 
 "        			ace.END_TIME as END_TIME," + 
 "        			round(extract('epoch' from (ace.END_TIME-ace.BEGIN_TIME))) as EXECUTION_TIME," + 
 "        			ace.EST_CD as STATUS," + 
 "        			max((case when acw.IS_IN is true then acw.WORKSPACE else null end)) as WORKSPACE_IN," + 
 "        			max((case when acw.IS_IN is false then acw.WORKSPACE else null end)) as WORKSPACE_OUT," + 
 "        			acl.ATTACHMENT is not null as HAS_ATTACHMENT," + 
 "        			acl.LOG is not null as HAS_TECHNICAL_LOG" + 
 "        	from o_activity_execution ace" + 
 "        	join o_activity act on act.ACT_ID = ace.ACT_ID" + 
 "        	join o_activity_workspace acw on acw.ACE_ID = ace.ACE_ID" + 
 "        	left join o_activity_log acl on acl.ACE_ID = ace.ACE_ID" + 
 "        	where ace.ACE_ID = #aceId#" + 
 "        	group by ace.ACE_ID, act.LABEL, ace.BEGIN_TIME, ace.END_TIME, acl.ATTACHMENT, acl.LOG",
			taskEngineClass = io.vertigo.dynamox.task.TaskEngineSelect.class)
	@io.vertigo.datamodel.task.proxy.TaskOutput(domain = "STyDtOActivityExecutionUi")
	public io.vertigo.orchestra.monitoring.domain.uiexecutions.OActivityExecutionUi getActivitiyByAceId(@io.vertigo.datamodel.task.proxy.TaskInput(name = "aceId", domain = "STyOIdentifiant") final Long aceId) {
		final Task task = createTaskBuilder("TkGetActivitiyByAceId")
				.addValue("aceId", aceId)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	/**
	 * Execute la tache StTkGetExecutionByPreId.
	 * @param preId Long
	 * @return OProcessExecutionUi dtOProcessExecutionUi
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			dataSpace = "orchestra",
			name = "TkGetExecutionByPreId",
			request = "select  pre.PRE_ID as PRE_ID," + 
 "        			pre.BEGIN_TIME as BEGIN_TIME," + 
 "        			pre.END_TIME as END_TIME," + 
 "        			round(extract('epoch' from (pre.END_TIME-pre.BEGIN_TIME))) as EXECUTION_TIME," + 
 "        			pre.EST_CD as STATUS," + 
 "        			pre.CHECKED as CHECKED," + 
 "        			pre.CHECKING_DATE as CHECKING_DATE," + 
 "        			pre.CHECKING_COMMENT as CHECKING_COMMENT," + 
 "        			(select " + 
 "			        	acl.attachment is not null" + 
 "						from o_activity_execution ace" + 
 "						left join o_activity_log acl on acl.ACE_ID = ace.ACE_ID" + 
 "						where ace.PRE_ID = #preId#" + 
 "						order by ace.end_time desc limit 1) as HAS_ATTACHMENT" + 
 "        	from o_process_execution pre   " + 
 "        	where pre.PRE_ID = #preId#",
			taskEngineClass = io.vertigo.dynamox.task.TaskEngineSelect.class)
	@io.vertigo.datamodel.task.proxy.TaskOutput(domain = "STyDtOProcessExecutionUi")
	public io.vertigo.orchestra.monitoring.domain.uiexecutions.OProcessExecutionUi getExecutionByPreId(@io.vertigo.datamodel.task.proxy.TaskInput(name = "preId", domain = "STyOIdentifiant") final Long preId) {
		final Task task = createTaskBuilder("TkGetExecutionByPreId")
				.addValue("preId", preId)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	/**
	 * Execute la tache StTkGetExecutionsByProcessName.
	 * @param name String
	 * @param status String
	 * @param limit Integer
	 * @param offset Integer
	 * @return DtList de OProcessExecutionUi dtcOProcessExecutionUi
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			dataSpace = "orchestra",
			name = "TkGetExecutionsByProcessName",
			request = "select  pre.PRE_ID as PRE_ID," + 
 "        			pre.BEGIN_TIME as BEGIN_TIME," + 
 "        			pre.END_TIME as END_TIME," + 
 "        			round(extract('epoch' from (pre.END_TIME-pre.BEGIN_TIME))) as EXECUTION_TIME," + 
 "        			pre.EST_CD as STATUS" + 
 "        	from o_process pro" + 
 "        	join o_process_execution pre on pro.PRO_ID = pre.PRO_ID" + 
 "        	where pro.NAME = #name#" + 
 "        	<%if (status != \"\") {%>" + 
 "        		and pre.EST_CD = #status#" + 
 "        	<%}%>" + 
 "        	order by pre.begin_time desc" + 
 "        	limit #limit#" + 
 "        	offset #offset#",
			taskEngineClass = io.vertigo.dynamox.task.TaskEngineSelect.class)
	@io.vertigo.datamodel.task.proxy.TaskOutput(domain = "STyDtOProcessExecutionUi")
	public io.vertigo.datamodel.structure.model.DtList<io.vertigo.orchestra.monitoring.domain.uiexecutions.OProcessExecutionUi> getExecutionsByProcessName(@io.vertigo.datamodel.task.proxy.TaskInput(name = "name", domain = "STyOLibelle") final String name, @io.vertigo.datamodel.task.proxy.TaskInput(name = "status", domain = "STyOCodeIdentifiant") final String status, @io.vertigo.datamodel.task.proxy.TaskInput(name = "limit", domain = "STyONombre") final Integer limit, @io.vertigo.datamodel.task.proxy.TaskInput(name = "offset", domain = "STyONombre") final Integer offset) {
		final Task task = createTaskBuilder("TkGetExecutionsByProcessName")
				.addValue("name", name)
				.addValue("status", status)
				.addValue("limit", limit)
				.addValue("offset", offset)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	private TaskManager getTaskManager() {
		return taskManager;
	}
}
