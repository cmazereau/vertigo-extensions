/**
 * vertigo - simple java starter
 *
 * Copyright (C) 2013-2019, vertigo-io, KleeGroup, direction.technique@kleegroup.com (http://www.kleegroup.com)
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
package io.vertigo.quarto.services.export.data.domain;

import io.vertigo.dynamo.domain.model.Entity;
import io.vertigo.dynamo.domain.model.UID;
import io.vertigo.dynamo.domain.stereotype.Field;
import io.vertigo.dynamo.domain.util.DtObjectUtil;

/**
 * Country.
 */
public final class Country implements Entity {
	/** SerialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long conId;
	private String name;
	private Boolean active;

	@Override
	public UID getUID() {
		return UID.of(this);
	}

	@Field(domain = "DoId", type = "ID", required = true, label = "id of the country")
	public final Long getId() {
		return id;
	}

	public Country setId(final Long id) {
		this.id = id;
		return this;
	}

	@Field(domain = "DoId", required = true, label = "id of the continent")
	public final Long getConId() {
		return conId;
	}

	public Country setConId(final Long conId) {
		this.conId = conId;
		return this;
	}

	@Field(domain = "DoString", label = "name")
	public final String getName() {
		return name;
	}

	public Country setName(final String name) {
		this.name = name;
		return this;
	}

	@Field(domain = "DoBoolean", label = "active")
	public final Boolean getActive() {
		return active;
	}

	public Country setActive(final boolean active) {
		this.active = active;
		return this;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
