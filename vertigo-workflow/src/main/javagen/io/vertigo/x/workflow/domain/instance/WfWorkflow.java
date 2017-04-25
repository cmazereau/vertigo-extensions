package io.vertigo.x.workflow.domain.instance;

import io.vertigo.dynamo.domain.model.Entity;
import io.vertigo.dynamo.domain.model.URI;
import io.vertigo.dynamo.domain.stereotype.Field;
import io.vertigo.dynamo.domain.util.DtObjectUtil;

/**
 * Attention cette classe est générée automatiquement !
 * Objet de données WfWorkflow
 */
public final class WfWorkflow implements Entity {

	/** SerialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Long wfwId;
	private java.util.Date creationDate;
	private Long itemId;
	private String username;
	private Boolean userLogic;
	private Long wfwdId;
	private String wfsCode;
	private Long wfaId2;
	private io.vertigo.x.workflow.domain.model.WfWorkflowDefinition wfWorkflowDefinition;
	private io.vertigo.x.workflow.domain.instance.WfActivity current;
	private io.vertigo.x.workflow.domain.instance.WfStatus wfStatus;

	/** {@inheritDoc} */
	@Override
	public URI<WfWorkflow> getURI() {
		return DtObjectUtil.createURI(this);
	}

	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'Id Workflow'.
	 * @return Long wfwId <b>Obligatoire</b>
	 */
	@Field(domain = "DO_X_WORKFLOW_ID", type = "ID", required = true, label = "Id Workflow")
	public Long getWfwId() {
		return wfwId;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'Id Workflow'.
	 * @param wfwId Long <b>Obligatoire</b>
	 */
	public void setWfwId(final Long wfwId) {
		this.wfwId = wfwId;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'creation date'.
	 * @return java.util.Date creationDate
	 */
	@Field(domain = "DO_X_WORKFLOW_DATE", label = "creation date")
	public java.util.Date getCreationDate() {
		return creationDate;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'creation date'.
	 * @param creationDate java.util.Date
	 */
	public void setCreationDate(final java.util.Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'itemId'.
	 * @return Long itemId
	 */
	@Field(domain = "DO_X_WORKFLOW_WEAK_ID", label = "itemId")
	public Long getItemId() {
		return itemId;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'itemId'.
	 * @param itemId Long
	 */
	public void setItemId(final Long itemId) {
		this.itemId = itemId;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'username'.
	 * @return String username
	 */
	@Field(domain = "DO_X_WORKFLOW_USER", label = "username")
	public String getUsername() {
		return username;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'username'.
	 * @param username String
	 */
	public void setUsername(final String username) {
		this.username = username;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'user_logic'.
	 * @return Boolean userLogic <b>Obligatoire</b>
	 */
	@Field(domain = "DO_X_WORKFLOW_FLAG", required = true, label = "user_logic")
	public Boolean getUserLogic() {
		return userLogic;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'user_logic'.
	 * @param userLogic Boolean <b>Obligatoire</b>
	 */
	public void setUserLogic(final Boolean userLogic) {
		this.userLogic = userLogic;
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'WfWorkflowDefinition'.
	 * @return Long wfwdId <b>Obligatoire</b>
	 */
	@Field(domain = "DO_X_WORKFLOW_ID", type = "FOREIGN_KEY", required = true, label = "WfWorkflowDefinition")
	public Long getWfwdId() {
		return wfwdId;
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'WfWorkflowDefinition'.
	 * @param wfwdId Long <b>Obligatoire</b>
	 */
	public void setWfwdId(final Long wfwdId) {
		this.wfwdId = wfwdId;
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'WfStatus'.
	 * @return String wfsCode <b>Obligatoire</b>
	 */
	@Field(domain = "DO_X_WORKFLOW_CODE", type = "FOREIGN_KEY", required = true, label = "WfStatus")
	public String getWfsCode() {
		return wfsCode;
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'WfStatus'.
	 * @param wfsCode String <b>Obligatoire</b>
	 */
	public void setWfsCode(final String wfsCode) {
		this.wfsCode = wfsCode;
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'current'.
	 * @return Long wfaId2
	 */
	@Field(domain = "DO_X_WORKFLOW_ID", type = "FOREIGN_KEY", label = "current")
	public Long getWfaId2() {
		return wfaId2;
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'current'.
	 * @param wfaId2 Long
	 */
	public void setWfaId2(final Long wfaId2) {
		this.wfaId2 = wfaId2;
	}

	/**
	 * Association : WfWorkflowDefinition.
	 * @return io.vertigo.x.workflow.domain.model.WfWorkflowDefinition
	 */
	public io.vertigo.x.workflow.domain.model.WfWorkflowDefinition getWfWorkflowDefinition() {
		final io.vertigo.dynamo.domain.model.URI<io.vertigo.x.workflow.domain.model.WfWorkflowDefinition> fkURI = getWfWorkflowDefinitionURI();
		if (fkURI == null) {
			return null;
		}
		//On est toujours dans un mode lazy. On s'assure cependant que l'objet associé n'a pas changé
		if (wfWorkflowDefinition == null || !fkURI.equals(wfWorkflowDefinition.getURI())) {
			wfWorkflowDefinition = io.vertigo.app.Home.getApp().getComponentSpace().resolve(io.vertigo.dynamo.store.StoreManager.class).getDataStore().readOne(fkURI);
		}
		return wfWorkflowDefinition;
	}

	/**
	 * Retourne l'URI: WfWorkflowDefinition.
	 * @return URI de l'association
	 */
	@io.vertigo.dynamo.domain.stereotype.Association(
			name = "A_WFWD_WFW",
			fkFieldName = "WFWD_ID",
			primaryDtDefinitionName = "DT_WF_WORKFLOW_DEFINITION",
			primaryIsNavigable = true,
			primaryRole = "WfWorkflowDefinition",
			primaryLabel = "WfWorkflowDefinition",
			primaryMultiplicity = "1..1",
			foreignDtDefinitionName = "DT_WF_WORKFLOW",
			foreignIsNavigable = false,
			foreignRole = "WfWorkflow",
			foreignLabel = "WfWorkflow",
			foreignMultiplicity = "0..*")
	public io.vertigo.dynamo.domain.model.URI<io.vertigo.x.workflow.domain.model.WfWorkflowDefinition> getWfWorkflowDefinitionURI() {
		return io.vertigo.dynamo.domain.util.DtObjectUtil.createURI(this, "A_WFWD_WFW", io.vertigo.x.workflow.domain.model.WfWorkflowDefinition.class);
	}

	// Association : WfActivity non navigable
	/**
	 * Association : current.
	 * @return io.vertigo.x.workflow.domain.instance.WfActivity
	 */
	public io.vertigo.x.workflow.domain.instance.WfActivity getCurrent() {
		final io.vertigo.dynamo.domain.model.URI<io.vertigo.x.workflow.domain.instance.WfActivity> fkURI = getCurrentURI();
		if (fkURI == null) {
			return null;
		}
		//On est toujours dans un mode lazy. On s'assure cependant que l'objet associé n'a pas changé
		if (current == null || !fkURI.equals(current.getURI())) {
			current = io.vertigo.app.Home.getApp().getComponentSpace().resolve(io.vertigo.dynamo.store.StoreManager.class).getDataStore().readOne(fkURI);
		}
		return current;
	}

	/**
	 * Retourne l'URI: current.
	 * @return URI de l'association
	 */
	@io.vertigo.dynamo.domain.stereotype.Association(
			name = "A_WFW_WFA_2",
			fkFieldName = "WFA_ID_2",
			primaryDtDefinitionName = "DT_WF_ACTIVITY",
			primaryIsNavigable = true,
			primaryRole = "Current",
			primaryLabel = "current",
			primaryMultiplicity = "0..1",
			foreignDtDefinitionName = "DT_WF_WORKFLOW",
			foreignIsNavigable = false,
			foreignRole = "WfWorkflow",
			foreignLabel = "WfWorkflow",
			foreignMultiplicity = "0..*")
	public io.vertigo.dynamo.domain.model.URI<io.vertigo.x.workflow.domain.instance.WfActivity> getCurrentURI() {
		return io.vertigo.dynamo.domain.util.DtObjectUtil.createURI(this, "A_WFW_WFA_2", io.vertigo.x.workflow.domain.instance.WfActivity.class);
	}

	/**
	 * Association : WfStatus.
	 * @return io.vertigo.x.workflow.domain.instance.WfStatus
	 */
	public io.vertigo.x.workflow.domain.instance.WfStatus getWfStatus() {
		final io.vertigo.dynamo.domain.model.URI<io.vertigo.x.workflow.domain.instance.WfStatus> fkURI = getWfStatusURI();
		if (fkURI == null) {
			return null;
		}
		//On est toujours dans un mode lazy. On s'assure cependant que l'objet associé n'a pas changé
		if (wfStatus == null || !fkURI.equals(wfStatus.getURI())) {
			wfStatus = io.vertigo.app.Home.getApp().getComponentSpace().resolve(io.vertigo.dynamo.store.StoreManager.class).getDataStore().readOne(fkURI);
		}
		return wfStatus;
	}

	/**
	 * Retourne l'URI: WfStatus.
	 * @return URI de l'association
	 */
	@io.vertigo.dynamo.domain.stereotype.Association(
			name = "A_WFW_WFS",
			fkFieldName = "WFS_CODE",
			primaryDtDefinitionName = "DT_WF_STATUS",
			primaryIsNavigable = true,
			primaryRole = "WfStatus",
			primaryLabel = "WfStatus",
			primaryMultiplicity = "1..1",
			foreignDtDefinitionName = "DT_WF_WORKFLOW",
			foreignIsNavigable = false,
			foreignRole = "WfWorkflow",
			foreignLabel = "WfWorkflow",
			foreignMultiplicity = "1..*")
	public io.vertigo.dynamo.domain.model.URI<io.vertigo.x.workflow.domain.instance.WfStatus> getWfStatusURI() {
		return io.vertigo.dynamo.domain.util.DtObjectUtil.createURI(this, "A_WFW_WFS", io.vertigo.x.workflow.domain.instance.WfStatus.class);
	}


	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}