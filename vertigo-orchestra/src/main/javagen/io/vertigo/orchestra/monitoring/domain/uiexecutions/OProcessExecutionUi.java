package io.vertigo.orchestra.monitoring.domain.uiexecutions;

import io.vertigo.core.lang.Generated;
import io.vertigo.datamodel.structure.model.DtObject;
import io.vertigo.datamodel.structure.stereotype.Field;
import io.vertigo.datamodel.structure.util.DtObjectUtil;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
@Generated
@io.vertigo.datamodel.smarttype.annotations.Mapper(clazz = io.vertigo.datamodel.structure.util.JsonMapper.class, dataType = io.vertigo.datamodel.structure.metamodel.DataType.String)
public final class OProcessExecutionUi implements DtObject {
	private static final long serialVersionUID = 1L;

	private Long preId;
	private java.time.Instant beginTime;
	private java.time.Instant endTime;
	private Integer executionTime;
	private String status;
	private Boolean checked;
	private java.time.Instant checkingDate;
	private String checkingComment;
	private Boolean hasAttachment;
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Id de l'activité'.
	 * @return Long preId <b>Obligatoire</b>
	 */
	@Field(domain = "STyOIdentifiant", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Id de l'activité")
	public Long getPreId() {
		return preId;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Id de l'activité'.
	 * @param preId Long <b>Obligatoire</b>
	 */
	public void setPreId(final Long preId) {
		this.preId = preId;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Démarrage'.
	 * @return Instant beginTime <b>Obligatoire</b>
	 */
	@Field(domain = "STyOTimestamp", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Démarrage")
	public java.time.Instant getBeginTime() {
		return beginTime;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Démarrage'.
	 * @param beginTime Instant <b>Obligatoire</b>
	 */
	public void setBeginTime(final java.time.Instant beginTime) {
		this.beginTime = beginTime;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Fin'.
	 * @return Instant endTime <b>Obligatoire</b>
	 */
	@Field(domain = "STyOTimestamp", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Fin")
	public java.time.Instant getEndTime() {
		return endTime;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Fin'.
	 * @param endTime Instant <b>Obligatoire</b>
	 */
	public void setEndTime(final java.time.Instant endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Durée'.
	 * @return Integer executionTime
	 */
	@Field(domain = "STyONombre", label = "Durée")
	public Integer getExecutionTime() {
		return executionTime;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Durée'.
	 * @param executionTime Integer
	 */
	public void setExecutionTime(final Integer executionTime) {
		this.executionTime = executionTime;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Statut'.
	 * @return String status
	 */
	@Field(domain = "STyOCodeIdentifiant", label = "Statut")
	public String getStatus() {
		return status;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Statut'.
	 * @param status String
	 */
	public void setStatus(final String status) {
		this.status = status;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Pris en charge'.
	 * @return Boolean checked
	 */
	@Field(domain = "STyOBooleen", label = "Pris en charge")
	public Boolean getChecked() {
		return checked;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Pris en charge'.
	 * @param checked Boolean
	 */
	public void setChecked(final Boolean checked) {
		this.checked = checked;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Date de prise en charge'.
	 * @return Instant checkingDate
	 */
	@Field(domain = "STyOTimestamp", label = "Date de prise en charge")
	public java.time.Instant getCheckingDate() {
		return checkingDate;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Date de prise en charge'.
	 * @param checkingDate Instant
	 */
	public void setCheckingDate(final java.time.Instant checkingDate) {
		this.checkingDate = checkingDate;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Commentaire'.
	 * @return String checkingComment
	 */
	@Field(domain = "STyOText", label = "Commentaire")
	public String getCheckingComment() {
		return checkingComment;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Commentaire'.
	 * @param checkingComment String
	 */
	public void setCheckingComment(final String checkingComment) {
		this.checkingComment = checkingComment;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Fichier de log'.
	 * @return Boolean hasAttachment
	 */
	@Field(domain = "STyOBooleen", label = "Fichier de log")
	public Boolean getHasAttachment() {
		return hasAttachment;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Fichier de log'.
	 * @param hasAttachment Boolean
	 */
	public void setHasAttachment(final Boolean hasAttachment) {
		this.hasAttachment = hasAttachment;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
