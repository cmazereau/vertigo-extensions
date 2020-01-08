package io.vertigo.orchestra.domain.referential;

import io.vertigo.core.lang.Generated;
import io.vertigo.dynamo.domain.model.Entity;
import io.vertigo.dynamo.domain.model.UID;
import io.vertigo.dynamo.domain.stereotype.Field;
import io.vertigo.dynamo.domain.util.DtObjectUtil;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
@Generated
@io.vertigo.dynamo.domain.stereotype.DataSpace("orchestra")
public final class OProcessType implements Entity {
	private static final long serialVersionUID = 1L;

	private String prtCd;
	private String label;

	/** {@inheritDoc} */
	@Override
	public UID<OProcessType> getUID() {
		return UID.of(this);
	}
	
	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'Code'.
	 * @return String prtCd <b>Obligatoire</b>
	 */
	@Field(domain = "DoOCodeIdentifiant", type = "ID", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Code")
	public String getPrtCd() {
		return prtCd;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'Code'.
	 * @param prtCd String <b>Obligatoire</b>
	 */
	public void setPrtCd(final String prtCd) {
		this.prtCd = prtCd;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Libellé'.
	 * @return String label
	 */
	@Field(domain = "DoOLibelle", label = "Libellé")
	public String getLabel() {
		return label;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Libellé'.
	 * @param label String
	 */
	public void setLabel(final String label) {
		this.label = label;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
