package io.vertigo.dynamo.environment.java.data.domain;

import io.vertigo.dynamo.domain.model.EnumVAccessor;
import io.vertigo.dynamo.domain.model.KeyConcept;
import io.vertigo.dynamo.domain.model.URI;
import io.vertigo.dynamo.domain.model.VAccessor;
import io.vertigo.dynamo.domain.stereotype.Field;
import io.vertigo.dynamo.domain.util.DtObjectUtil;
import io.vertigo.lang.Generated;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
@Generated
@javax.persistence.Entity
@javax.persistence.Table(name = "COMMAND")
public final class Command implements KeyConcept {
	private static final long serialVersionUID = 1L;

	private Long cmdId;

	@javax.persistence.Transient
	@io.vertigo.dynamo.domain.stereotype.Association(
			name = "A_CTY_CMD",
			fkFieldName = "CTY_ID",
			primaryDtDefinitionName = "DT_COMMAND_TYPE",
			primaryIsNavigable = true,
			primaryRole = "CommandType",
			primaryLabel = "Command type",
			primaryMultiplicity = "0..1",
			foreignDtDefinitionName = "DT_COMMAND",
			foreignIsNavigable = false,
			foreignRole = "Command",
			foreignLabel = "Command",
			foreignMultiplicity = "0..*")
	private final EnumVAccessor<io.vertigo.dynamo.environment.java.data.domain.CommandType, io.vertigo.dynamo.environment.java.data.domain.CommandTypeEnum> ctyIdAccessor = new EnumVAccessor<>(io.vertigo.dynamo.environment.java.data.domain.CommandType.class, "commandType", io.vertigo.dynamo.environment.java.data.domain.CommandTypeEnum.class);

	@javax.persistence.Transient
	@io.vertigo.dynamo.domain.stereotype.Association(
			name = "A_CIT_CMD",
			fkFieldName = "CIT_ID",
			primaryDtDefinitionName = "DT_CITY",
			primaryIsNavigable = true,
			primaryRole = "City",
			primaryLabel = "City",
			primaryMultiplicity = "0..1",
			foreignDtDefinitionName = "DT_COMMAND",
			foreignIsNavigable = false,
			foreignRole = "Command",
			foreignLabel = "Command",
			foreignMultiplicity = "0..*")
	private final VAccessor<io.vertigo.dynamo.environment.java.data.domain.City> citIdAccessor = new VAccessor<>(io.vertigo.dynamo.environment.java.data.domain.City.class, "city");

	private io.vertigo.dynamo.domain.model.DtList<io.vertigo.dynamo.environment.java.data.domain.Attachment> attachment;

	/** {@inheritDoc} */
	@javax.persistence.Transient
	@Override
	public URI<Command> getURI() {
		return DtObjectUtil.createURI(this);
	}

	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'id'.
	 * @return Long cmdId <b>Obligatoire</b>
	 */
	@javax.persistence.Id
	@javax.persistence.SequenceGenerator(name = "sequence", sequenceName = "SEQ_COMMAND", allocationSize = 1)
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "sequence")
	@javax.persistence.Column(name = "CMD_ID")
	@Field(domain = "DO_ID", type = "ID", required = true, label = "id")
	public Long getCmdId() {
		return cmdId;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'id'.
	 * @param cmdId Long <b>Obligatoire</b>
	 */
	public void setCmdId(final Long cmdId) {
		this.cmdId = cmdId;
	}

	/**
	 * Récupère la valeur de la propriété 'CommandType' as an enum.
	 * @return the enum value
	 */
	public io.vertigo.dynamo.environment.java.data.domain.CommandTypeEnum getCommandTypeEnum() {
		return ctyIdAccessor.getEnumValue();
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'CommandType' as an enum.
	 * @param commandType io.vertigo.dynamo.environment.java.data.domain.CommandTypeEnum
	 */
	public void setCommandTypeEnum(final io.vertigo.dynamo.environment.java.data.domain.CommandTypeEnum commandTypeEnum) {
		ctyIdAccessor.setEnumValue(commandTypeEnum);
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'Command type'.
	 * @return Long ctyId
	 */
	@javax.persistence.Column(name = "CTY_ID")
	@Field(domain = "DO_ID", type = "FOREIGN_KEY", label = "Command type")
	public Long getCtyId() {
		return (Long) ctyIdAccessor.getId();
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'Command type'.
	 * @param ctyId Long
	 */
	public void setCtyId(final Long ctyId) {
		ctyIdAccessor.setId(ctyId);
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'City'.
	 * @return Long citId
	 */
	@javax.persistence.Column(name = "CIT_ID")
	@Field(domain = "DO_ID", type = "FOREIGN_KEY", label = "City")
	public Long getCitId() {
		return (Long) citIdAccessor.getId();
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'City'.
	 * @param citId Long
	 */
	public void setCitId(final Long citId) {
		citIdAccessor.setId(citId);
	}

	/**
	 * Association : City.
	 * @return io.vertigo.dynamo.environment.java.data.domain.City
	 */

	public io.vertigo.dynamo.environment.java.data.domain.City getCity() {
		return citIdAccessor.get();
	}

	/**
	 * Retourne l'URI: City.
	 * @return URI de l'association
	 */
	public io.vertigo.dynamo.domain.model.URI<io.vertigo.dynamo.environment.java.data.domain.City> getCityURI() {
		return citIdAccessor.getURI();
	}

	/**
	 * Association : Attachment.
	 * @return io.vertigo.dynamo.domain.model.DtList<io.vertigo.dynamo.environment.java.data.domain.Attachment>
	 */
	public io.vertigo.dynamo.domain.model.DtList<io.vertigo.dynamo.environment.java.data.domain.Attachment> getAttachmentList() {
		// On doit avoir une clé primaire renseignée. Si ce n'est pas le cas, on renvoie une liste vide
		if (io.vertigo.dynamo.domain.util.DtObjectUtil.getId(this) == null) {
			return new io.vertigo.dynamo.domain.model.DtList<>(io.vertigo.dynamo.environment.java.data.domain.Attachment.class);
		}
		final io.vertigo.dynamo.domain.model.DtListURI fkDtListURI = getAttachmentDtListURI();
		io.vertigo.lang.Assertion.checkNotNull(fkDtListURI);
		//---------------------------------------------------------------------
		//On est toujours dans un mode lazy.
		if (attachment == null) {
			attachment = io.vertigo.app.Home.getApp().getComponentSpace().resolve(io.vertigo.dynamo.store.StoreManager.class).getDataStore().findAll(fkDtListURI);
		}
		return attachment;
	}

	/**
	 * Association URI: Attachment.
	 * @return URI de l'association
	 */

	public io.vertigo.dynamo.domain.metamodel.association.DtListURIForSimpleAssociation getAttachmentDtListURI() {
		return io.vertigo.dynamo.domain.util.DtObjectUtil.createDtListURIForSimpleAssociation(this, "A_CMD_ATT", "Attachment");
	}

	/**
	 * Association : Command type.
	 * @return io.vertigo.dynamo.environment.java.data.domain.CommandType
	 */

	public io.vertigo.dynamo.environment.java.data.domain.CommandType getCommandType() {
		return ctyIdAccessor.get();
	}

	/**
	 * Retourne l'URI: Command type.
	 * @return URI de l'association
	 */
	public io.vertigo.dynamo.domain.model.URI<io.vertigo.dynamo.environment.java.data.domain.CommandType> getCommandTypeURI() {
		return ctyIdAccessor.getURI();
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
