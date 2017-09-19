package io.vertigo.dynamo.environment.java.data.domain;

import io.vertigo.dynamo.domain.model.DtMasterData;
import io.vertigo.dynamo.domain.model.URI;
import io.vertigo.dynamo.domain.stereotype.Field;
import io.vertigo.dynamo.domain.util.DtObjectUtil;
import io.vertigo.lang.Generated;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
 @Generated
@javax.persistence.Entity
@javax.persistence.Table (name = "CITY")
public final class City implements DtMasterData {
	private static final long serialVersionUID = 1L;

	private Long citId;

	private String label;

	private String postalCode;


	/** {@inheritDoc} */
	@javax.persistence.Transient
	@Override
	public URI<City> getURI() {
		return DtObjectUtil.createURI(this);
	}

	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'id'.
	 * @return Long citId <b>Obligatoire</b>
	 */
	@javax.persistence.Id
	@javax.persistence.SequenceGenerator(name = "sequence", sequenceName = "SEQ_CITY", allocationSize=1)
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "sequence")
	@javax.persistence.Column(name = "CIT_ID")
	@Field(domain = "DO_ID", type = "ID", required = true, label = "id")
	public Long getCitId() {
		return citId;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'id'.
	 * @param citId Long <b>Obligatoire</b>
	 */
	public void setCitId(final Long citId) {
		this.citId = citId;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Label'.
	 * @return String label <b>Obligatoire</b>
	 */
	@javax.persistence.Column(name = "LABEL")
	@Field(domain = "DO_FULL_TEXT", required = true, label = "Label")
	public String getLabel() {
		return label;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Label'.
	 * @param label String <b>Obligatoire</b>
	 */
	public void setLabel(final String label) {
		this.label = label;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Postal code'.
	 * @return String postalCode <b>Obligatoire</b>
	 */
	@javax.persistence.Column(name = "POSTAL_CODE")
	@Field(domain = "DO_KEYWORD", required = true, label = "Postal code")
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Postal code'.
	 * @param postalCode String <b>Obligatoire</b>
	 */
	public void setPostalCode(final String postalCode) {
		this.postalCode = postalCode;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
