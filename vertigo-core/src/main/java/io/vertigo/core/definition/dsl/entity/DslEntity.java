/**
 * vertigo - simple java starter
 *
 * Copyright (C) 2013-2016, KleeGroup, direction.technique@kleegroup.com (http://www.kleegroup.com)
 * KleeGroup, Centre d'affaire la Boursidiere - BP 159 - 92357 Le Plessis Robinson Cedex - France
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.vertigo.core.definition.dsl.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import io.vertigo.lang.Assertion;

/**
 * Une entité permet de décrire un modèle, une classe.
 * - Elle est définie par son nom.
 * - Elle possède une liste de propriétés (Chacune étant obligatoire / facultative)
 * - Elle est composée d'une liste d'attibuts.
 *
 * Une entité permet, ainsi, d'adopter des comportement dynamique, de fabriquer des grammaires.
 * Si l'ensemble des définitions permet de construire le modèle, l'ensemble des entités permet de décrire le métamodèle.
 *
 * @author pchretien
 */
public final class DslEntity implements DslEntityFieldType {
	/**
	 * Nom de la metadefinition (Type de la définition).
	 */
	private final String name;

	/**
	 * Map : Field by names
	 */
	private final Map<String, DslEntityField> fields;

	private final boolean root;

	/**
	 * Constructeur de la MetaDefinition
	 * Une instance de MetaDefinition correspond à une classe -ou une interface- de Definition
	 * (Exemple : Classe Service).
	 * @param name Classe représentant l'instance métaDéfinition
	 */
	DslEntity(final String name, final Set<DslEntityField> fields, final boolean root) {
		Assertion.checkNotNull(name);
		Assertion.checkNotNull(fields);
		//-----
		this.name = name;
		this.root = root;
		this.fields = new HashMap<>();
		for (final DslEntityField field : fields) {
			Assertion.checkArgument(!this.fields.containsKey(field.getName()), "field {0} is already registered for {1}", field, this);
			//Une propriété est unique pour une définition donnée.
			//Il n'y a jamais de multiplicité
			this.fields.put(field.getName(), field);
		}
	}

	/**
	 * @return Nom de l'entité (Type de la définition).
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Ensemble de toutes les propriétés gérées (obligatoires ou non).
	 */
	public Set<String> getPropertyNames() {
		final Set<String> names = new HashSet<>();
		for (final DslEntityField field : fields.values()) {
			if (field.getType().isProperty()) {
				names.add(field.getName());
			}
		}
		return names;
	}

	public DslPropertyType getPropertyType(final String fieldName) {
		final DslEntityFieldType type = getField(fieldName).getType();
		Assertion.checkArgument(type.isProperty(), "property {0} not found on {1}", fieldName, this);
		//-----
		return (DslPropertyType) type;
	}

	/**
	 * Returns the value to which the specified name is mapped.
	 * @param fieldName Name of the field
	 * @return Field
	 */
	public DslEntityField getField(final String fieldName) {
		Assertion.checkNotNull(fieldName);
		Assertion.checkArgument(fields.containsKey(fieldName), "la propriete {0} n'est pas declaree pour {1}", fieldName, this);
		//-----
		return fields.get(fieldName);
	}

	/**
	 * @return List of the entity's fields
	 */
	public Set<DslEntityField> getAttributes() {
		final Set<DslEntityField> attributes = new HashSet<>();
		for (final DslEntityField field : fields.values()) {
			if (!field.getType().isProperty()) {
				attributes.add(field);
			}
		}
		return attributes;
	}

	public DslEntityLink getLink() {
		return new DslEntityLink(this);
	}

	@Override
	public String toString() {
		return name;
	}

	public boolean isRoot() {
		return root;
	}

	@Override
	public boolean isProperty() {
		return false;
	}

	@Override
	public boolean isEntityLink() {
		return false;
	}

	@Override
	public boolean isEntity() {
		return true;
	}
}
