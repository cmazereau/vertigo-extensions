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
package io.vertigo.ledger;

import io.vertigo.app.config.NodeConfig;
import io.vertigo.commons.CommonsFeatures;
import io.vertigo.core.param.Param;

/**
 * Config for Junit
 *
 * @author xdurand
 *
 */
public class MyNodeConfig {

	/**
	 * Configuration de l'application pour Junit
	 *
	 * @return NodeConfig for Junit
	 */
	public static NodeConfig config() {
		return NodeConfig.builder()
				.beginBoot()
				.withLocales("fr")
				.endBoot()
				.addModule(new CommonsFeatures().build())
				.addModule(new LedgerFeatures()
						.withEthereumBlockChain(
								Param.of("urlRpcEthNode", "http://docker-vertigo:8545"),
								Param.of("myAccountName", "Bob"),
								Param.of("myPublicAddr", "0x9a48b59e301794298fdc0f945da3fbd58cff5beb"),
								Param.of("defaultDestAccountName", "Bob"),
								Param.of("defaultDestPublicAddr", "0x9a48b59e301794298fdc0f945da3fbd58cff5beb"),
								Param.of("walletPassword", "mypassword"),
								Param.of("walletPath", "UTC--2018-08-31T12-39-11.923861245Z--2e61cf9f966d4e66a0912d3116008cf8e47cb32e"))
						.build())
				.build();
	}
}
