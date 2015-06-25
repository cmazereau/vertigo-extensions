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
package io.vertigo.dynamo.persistence;

import io.vertigo.commons.event.EventChannel;
import io.vertigo.dynamo.domain.model.URI;
import io.vertigo.dynamo.persistence.datastore.Broker;
import io.vertigo.dynamo.persistence.datastore.BrokerConfig;
import io.vertigo.dynamo.persistence.datastore.BrokerNN;
import io.vertigo.dynamo.persistence.datastore.MasterDataConfig;
import io.vertigo.dynamo.persistence.filestore.FileInfoBroker;
import io.vertigo.lang.Component;

/**
* Gestionnaire des données et des accès aux données.
*
* @author pchretien
*/
public interface PersistenceManager extends Component {

	/**
	 * ChannelNamed events fired.
	 */
	enum FiredEvent implements EventChannel<URI> {
		storeCreate,
		storeUpdate,
		storeDelete
	}

	/**
	 * @return Broker d'objets métier
	 */
	FileInfoBroker getFileInfoBroker();

	/**
	 * @return Broker d'objets métier
	 */
	Broker getBroker();

	BrokerNN getBrokerNN();

	/**
	 * @return Configuration du composant de persistance
	 */
	BrokerConfig getBrokerConfig();

	/**
	 * @return Configuration MDM
	 */
	MasterDataConfig getMasterDataConfig();
}
