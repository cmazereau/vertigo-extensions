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
package io.vertigo.datafactory.search;

import java.util.Collections;
import java.util.List;

import io.vertigo.commons.cache.CacheDefinition;
import io.vertigo.core.node.definition.Definition;
import io.vertigo.core.node.definition.DefinitionSpace;
import io.vertigo.core.node.definition.SimpleDefinitionProvider;
import io.vertigo.datafactory.search.data.domain.Item;
import io.vertigo.datastore.impl.entitystore.cache.CacheData;
import io.vertigo.dynamo.domain.util.DtObjectUtil;

/**
 * Initialisation des listes de références.
 *
 * @author jmforhan
 */
public class StoreCacheDefinitionProvider implements SimpleDefinitionProvider {

	@Override
	public List<? extends Definition> provideDefinitions(final DefinitionSpace definitionSpace) {
		return Collections.singletonList(new CacheDefinition(CacheData.getContext(DtObjectUtil.findDtDefinition(Item.class)), true, 1000, 3600, 3600 / 2, true));
	}

}