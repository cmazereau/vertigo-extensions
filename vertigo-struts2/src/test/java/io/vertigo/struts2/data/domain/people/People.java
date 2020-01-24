package io.vertigo.struts2.data.domain.people;

import io.vertigo.core.lang.Generated;
import io.vertigo.datamodel.structure.model.KeyConcept;
import io.vertigo.datamodel.structure.model.UID;
import io.vertigo.datamodel.structure.stereotype.Field;
import io.vertigo.datamodel.structure.util.DtObjectUtil;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
@Generated
@io.vertigo.datamodel.smarttype.annotations.Mapper(clazz = io.vertigo.datamodel.structure.util.JsonMapper.class, dataType = io.vertigo.datamodel.structure.metamodel.DataType.String)
public final class People implements KeyConcept {
	private static final long serialVersionUID = 1L;

	private Long peoId;
	private String lastName;
	private String firstName;
	private String peoName;
	private String imdbid;

	/** {@inheritDoc} */
	@Override
	public UID<People> getUID() {
		return UID.of(this);
	}

	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'PEO_ID'.
	 * @return Long peoId <b>Obligatoire</b>
	 */
	@Field(domain = "STyId", type = "ID", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "PEO_ID")
	public Long getPeoId() {
		return peoId;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'PEO_ID'.
	 * @param peoId Long <b>Obligatoire</b>
	 */
	public void setPeoId(final Long peoId) {
		this.peoId = peoId;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Last Name'.
	 * @return String lastName
	 */
	@Field(domain = "STyName", label = "Last Name")
	public String getLastName() {
		return lastName;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Last Name'.
	 * @param lastName String
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'First Name'.
	 * @return String firstName
	 */
	@Field(domain = "STyFirstname", label = "First Name")
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'First Name'.
	 * @param firstName String
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Peo Name'.
	 * @return String peoName
	 */
	@Field(domain = "STyLabelLong", label = "Peo Name")
	public String getPeoName() {
		return peoName;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Peo Name'.
	 * @param peoName String
	 */
	public void setPeoName(final String peoName) {
		this.peoName = peoName;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'imdbID'.
	 * @return String imdbid
	 */
	@Field(domain = "STyLabel", label = "imdbID")
	public String getImdbid() {
		return imdbid;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'imdbID'.
	 * @param imdbid String
	 */
	public void setImdbid(final String imdbid) {
		this.imdbid = imdbid;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
