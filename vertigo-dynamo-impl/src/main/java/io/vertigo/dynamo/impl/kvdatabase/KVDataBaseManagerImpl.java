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
package io.vertigo.dynamo.impl.kvdatabase;

import io.vertigo.dynamo.kvdatabase.KVDataBaseManager;
import io.vertigo.dynamo.kvdatabase.KVStore;
import io.vertigo.lang.Assertion;
import io.vertigo.util.MapBuilder;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
* Standard implementation of the Key-Value DataBase.
*
* @author pchretien
*/
public final class KVDataBaseManagerImpl implements KVDataBaseManager {
	private final Map<String, KVStore> kvStoreByCollection;

	/**
	 * Constructeur.
	 * @param cacheManager Manager de gestion du cache
	 * @param collectionsManager Manager de gestion des collections
	 */
	@Inject
	public KVDataBaseManagerImpl(final List<KVDataStorePlugin> kvDataStorePlugins) {
		Assertion.checkNotNull(kvDataStorePlugins);
		//-----
		MapBuilder<String, KVStore> mapBuilder = new MapBuilder<>();
		for (KVDataStorePlugin kvDataStorePlugin : kvDataStorePlugins) {
			for (String collection : kvDataStorePlugin.getCollections()) {
				KVStore kvStore = new KVStoreImpl(collection, kvDataStorePlugin);
				mapBuilder.putCheckKeyNotExists(collection, kvStore);
			}
		}
		kvStoreByCollection = mapBuilder.unmodifiable().build();
	}

	/** {@inheritDoc} */
	@Override
	public KVStore getKVStore(String collection) {
		Assertion.checkArgNotEmpty(collection);
		//-----
		KVStore kvStore = kvStoreByCollection.get(collection);
		Assertion.checkNotNull(kvStore, "no store found for collection '{0}'", collection);
		return kvStore;
	}
}
