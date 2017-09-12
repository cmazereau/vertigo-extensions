/**
 * vertigo - simple java starter
 *
 * Copyright (C) 2013-2017, KleeGroup, direction.technique@kleegroup.com (http://www.kleegroup.com)
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
package io.vertigo.account.authorization.metamodel;

import java.util.List;

import io.vertigo.core.definition.Definition;
import io.vertigo.core.definition.DefinitionPrefix;
import io.vertigo.lang.Assertion;

/**
 * Un rôle est la réunion d'un ensemble de authorizations.
 * Un utilisateur peut avoir  plusieurs rôles.
 *
 * @author prahmoune, npiedeloup
 */
@DefinitionPrefix("R_")
public final class Role implements Definition {
	private final String name;
	private final String description;
	private final List<Authorization> authorizations;

	/**
	 * Constructor.
	 *
	 * @param name Nom du rôle
	 * @param description Description du rôle
	 * @param authorizations Liste des authorizations associées au rôle
	 */
	public Role(final String name, final String description, final List<Authorization> authorizations) {
		Assertion.checkArgNotEmpty(name);
		Assertion.checkArgNotEmpty(description);
		Assertion.checkNotNull(authorizations);
		//-----
		this.name = name;
		this.description = description;
		this.authorizations = authorizations;
	}

	/**
	 * @return Description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return Liste des authorizations
	 */
	public List<Authorization> getAuthorizations() {
		return authorizations;
	}

	/**
	 * @return Nom du rôle
	 */
	@Override
	public String getName() {
		return name;
	}
}
