/**
 * vertigo - simple java starter
 *
 * Copyright (C) 2013-2019, Vertigo.io, KleeGroup, direction.technique@kleegroup.com (http://www.kleegroup.com)
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
package io.vertigo.datafactory.collections.data.domain;

import io.vertigo.core.lang.Cardinality;
import io.vertigo.dynamo.domain.model.KeyConcept;
import io.vertigo.dynamo.domain.model.UID;
import io.vertigo.dynamo.domain.stereotype.Field;
import io.vertigo.dynamo.domain.util.DtObjectUtil;

public final class SmartCar implements KeyConcept {
	/** SerialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Field(domain = "DoId", type = "ID", cardinality = Cardinality.ONE, label = "identifiant de la voiture")
	private Long id;
	@Field(domain = "DoKeyword", cardinality = Cardinality.ONE, label = "Constructeur")
	private String manufacturer;
	@Field(domain = "DoInteger", cardinality = Cardinality.ONE, label = "Année")
	private Integer year;
	@Field(domain = "DoText", cardinality = Cardinality.ONE, label = "Descriptif")
	private String description;

	/** {@inheritDoc} */
	@Override
	public UID<SmartCar> getUID() {
		return UID.of(this);
	}

	public final Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(final String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(final Integer year) {
		this.year = year;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}