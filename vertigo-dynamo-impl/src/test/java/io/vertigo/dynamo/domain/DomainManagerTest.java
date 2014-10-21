/**
 * vertigo - simple java starter
 *
 * Copyright (C) 2013, KleeGroup, direction.technique@kleegroup.com (http://www.kleegroup.com)
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
package io.vertigo.dynamo.domain;

import io.vertigo.dynamo.domain.metamodel.DataType;
import io.vertigo.dynamo.domain.metamodel.Domain;
import io.vertigo.dynamo.domain.metamodel.DtDefinition;
import io.vertigo.dynamo.domain.metamodel.DtDefinitionBuilder;
import io.vertigo.dynamo.domain.metamodel.Formatter;
import io.vertigo.dynamo.domain.model.DtObject;
import io.vertigo.dynamo.domain.util.DtObjectUtil;
import io.vertigo.dynamox.domain.formatter.FormatterDefault;

import org.junit.Assert;
import org.junit.Test;

public class DomainManagerTest {

	@Test
	public void createDtDefinitionTest() {
		final Formatter formatter = new FormatterDefault("FMT_DEF");
		final Domain domain = new Domain("DO_NAME", DataType.String, formatter);

		final DtDefinition dtDefinition = new DtDefinitionBuilder("DT_MOVIE")
				.withPersistent(false)
				.withDynamic(true)
				.withDataField("NAME", "nom du film", domain, true, true, false, false)
				.build();

		final DtObject dto = DtObjectUtil.createDtObject(dtDefinition);
		dtDefinition.getField("NAME").getDataAccessor().setValue(dto, "dupond");

		Assert.assertEquals("dupond", dtDefinition.getField("NAME").getDataAccessor().getValue(dto));
	}
}
