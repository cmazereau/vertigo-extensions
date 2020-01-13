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
package io.vertigo.datafactory.search.dynamic;

import io.vertigo.core.node.config.NodeConfig;
import io.vertigo.datafactory.search.AbstractSearchManagerTest;
import io.vertigo.datafactory.search.MyNodeConfig;

/**
 * @author  npiedeloup
 */
public class RestHLSearchManagerDynaFieldsTest extends AbstractSearchManagerTest {
	//Index
	private static final String IDX_DYNA_ITEM = "IdxDynaItem";

	/**{@inheritDoc}*/
	@Override
	protected void doSetUp() {
		//attention : la première utilisation de l'index fige la définition des types
		init(IDX_DYNA_ITEM);
	}

	@Override
	protected NodeConfig buildNodeConfig() {
		return MyNodeConfig.config(true, false);
	}
}