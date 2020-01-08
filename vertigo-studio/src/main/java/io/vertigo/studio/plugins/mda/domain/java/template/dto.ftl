package ${dtDefinition.packageName};

import io.vertigo.core.lang.Generated;
import ${dtDefinition.stereotypePackageName};
<#if dtDefinition.containsEnumAccessor()>
import io.vertigo.dynamo.impl.store.datastore.EnumStoreVAccessor;
</#if>
<#if dtDefinition.containsListAccessor()>
import io.vertigo.dynamo.impl.store.datastore.StoreListVAccessor;
</#if>
<#if dtDefinition.entity || dtDefinition.fragment>
import io.vertigo.dynamo.domain.model.UID;
</#if>
<#if dtDefinition.containsAccessor()>
import io.vertigo.dynamo.impl.store.datastore.StoreVAccessor;
</#if>	
import io.vertigo.dynamo.domain.stereotype.Field;
import io.vertigo.dynamo.domain.util.DtObjectUtil;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
@Generated
<#list annotations(dtDefinition.dtDefinition) as annotation>
${annotation}
</#list>
public final class ${dtDefinition.classSimpleName} implements ${dtDefinition.stereotypeInterfaceName} {
	private static final long serialVersionUID = 1L;

	<#list dtDefinition.fields as dtField>
		<#if dtField.foreignKey>

			<#list annotations(dtField.association.definition) as annotation>
	${annotation}
			</#list>
			<#if dtField.association.targetStaticMasterData>
	private final EnumStoreVAccessor<${dtField.association.returnType}, ${dtField.association.returnType}Enum> ${dtField.name}Accessor = new EnumStoreVAccessor<>(${dtField.association.returnType}.class, "${dtField.association.role}", ${dtField.association.returnType}Enum.class);
			<#else>
	private final StoreVAccessor<${dtField.association.returnType}> ${dtField.name?uncap_first}Accessor = new StoreVAccessor<>(${dtField.association.returnType}.class, "${dtField.association.role}");
			</#if>
		<#else>
	private ${dtField.javaType} ${dtField.name};
		</#if>
	</#list>
	<#list dtDefinition.associations as association>
		<#if association.multiple>
			<#if (association.simple && association.navigable) || !association.simple >

			<#list annotations(association.definition) as annotation>
	${annotation}
			</#list>
	private final StoreListVAccessor<${association.returnType}> ${association.role?uncap_first}Accessor = new StoreListVAccessor<>(this, "${association.urn}", "${association.role}");
			</#if>
		</#if>
	</#list>
	<#if dtDefinition.entity>

	/** {@inheritDoc} */
		<#list annotations("UID") as annotation>
	${annotation}
		</#list>
	@Override
	public UID<${dtDefinition.classSimpleName}> getUID() {
		return UID.of(this);
	}
	</#if>
	<#if dtDefinition.fragment>

	/** {@inheritDoc} */
		<#list annotations("UID") as annotation>
	${annotation}
		</#list>
	@Override
	public UID<${dtDefinition.entityClassSimpleName}> getEntityUID() {
		return DtObjectUtil.createEntityUID(this); 
	}
	</#if>
	<#list dtDefinition.fields as dtField>
	<#if dtField.foreignKey>
	
	/**
	 * Champ : ${dtField.type}.
	 * Récupère la valeur de la propriété '${dtField.display}'.
	 * @return ${dtField.javaTypeLabel} ${dtField.name}<#if dtField.required> <b>Obligatoire</b></#if>
	 */
		<#list annotations(dtField) as annotation>
	${annotation}
		</#list>
	public ${dtField.javaType} get${dtField.name?cap_first}() {
		return (${dtField.javaType}) ${dtField.name}Accessor.getId();
	}

	/**
	 * Champ : ${dtField.type}.
	 * Définit la valeur de la propriété '${dtField.display}'.
	 * @param ${dtField.name} ${dtField.javaTypeLabel}<#if dtField.required> <b>Obligatoire</b></#if>
	 */
	public void set${dtField.name?cap_first}(final ${dtField.javaType} ${dtField.name}) {
		${dtField.name}Accessor.setId(${dtField.name});
	}
	<#else>
	
	/**
	 * Champ : ${dtField.type}.
	 * Récupère la valeur de la propriété '${dtField.display}'.
	 * @return ${dtField.javaTypeLabel} ${dtField.name}<#if dtField.required> <b>Obligatoire</b></#if>
	 */
		<#list annotations(dtField) as annotation>
	${annotation}
		</#list>
	public ${dtField.javaType} get${dtField.name?cap_first}() {
		return ${dtField.name};
	}

	/**
	 * Champ : ${dtField.type}.
	 * Définit la valeur de la propriété '${dtField.display}'.
	 * @param ${dtField.name} ${dtField.javaTypeLabel}<#if dtField.required> <b>Obligatoire</b></#if>
	 */
	public void set${dtField.name?cap_first}(final ${dtField.javaType} ${dtField.name}) {
		this.${dtField.name} = ${dtField.name};
	}
	</#if>
	</#list>
	<#list dtDefinition.dtComputedFields as dtField>
	
	/**
	 * Champ : ${dtField.type}.
	 * Récupère la valeur de la propriété calculée '${dtField.display}'.
	 * @return ${dtField.javaTypeLabel} ${dtField.name}<#if dtField.required> <b>Obligatoire</b></#if>
	 */
		<#list annotations(dtField) as annotation>
	${annotation}
		</#list>
	public ${dtField.javaType} get${dtField.name?cap_first}() {
		${dtField.javaCode}
	}
	</#list>
	<#if dtDefinition.associations?has_content>
		<#list dtDefinition.associations as association>
			<#if !association.multiple>

 	/**
	 * Association : ${association.label}.
	 * @return l'accesseur vers la propriété '${association.label}'
	 */
		<#if association.targetStaticMasterData>
	public EnumStoreVAccessor<${association.returnType}, ${association.returnType}Enum> ${association.role?uncap_first}() {
		<#else>
	public StoreVAccessor<${association.returnType}> ${association.role?uncap_first}() {
		</#if>
		return ${association.fkFieldName}Accessor;
	}
		<#elseif association.navigable ><#-- multiple and navigable -->

	/**
	 * Association : ${association.label}.
	 * @return l'accesseur vers la propriété '${association.label}'
	 */
	public StoreListVAccessor<${association.returnType}> ${association.role?uncap_first}() {
		return ${association.role?uncap_first}Accessor;
	}
			</#if>
		</#list>
	</#if>
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
