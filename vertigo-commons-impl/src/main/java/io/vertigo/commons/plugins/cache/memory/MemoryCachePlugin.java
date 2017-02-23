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
package io.vertigo.commons.plugins.cache.memory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.vertigo.commons.cache.CacheConfig;
import io.vertigo.commons.codec.CodecManager;
import io.vertigo.commons.impl.cache.CachePlugin;
import io.vertigo.core.component.ComponentInfo;
import io.vertigo.lang.Assertion;
import io.vertigo.lang.Describable;

/**
 * Implémentation MapCache du plugins.
 *
 * @author npiedeloup
 */
public final class MemoryCachePlugin implements CachePlugin, Describable {
	private final CodecManager codecManager;
	private final Map<String, CacheConfig> cacheConfigsPerContext = new HashMap<>();
	private final Map<String, MemoryCache> cachesPerContext = new HashMap<>();

	/**
	 * Constructeur.
	 * @param codecManager Manager des mécanismes de codage/décodage.
	 */
	@Inject
	public MemoryCachePlugin(final CodecManager codecManager) {
		Assertion.checkNotNull(codecManager);
		//-----
		this.codecManager = codecManager;
	}

	/** {@inheritDoc} */
	@Override
	public synchronized void addCache(final String context, final CacheConfig cacheConfig) {
		if (!cachesPerContext.containsKey(context)) {
			final MemoryCache cache = new MemoryCache(context, cacheConfig.getTimeToLiveSeconds());
			cachesPerContext.put(context, cache);
			cacheConfigsPerContext.put(context, cacheConfig);
		}
	}

	/** {@inheritDoc} */
	@Override
	public void put(final String context, final Serializable key, final Object value) {
		Assertion.checkNotNull(value, "CachePlugin can't cache null value. (context: {0}, key:{1})", context, key);
		Assertion.checkState(!(value instanceof byte[]), "Ce CachePlugin ne permet pas de mettre en cache des byte[].");
		//-----
		//On regarde la conf du cache pour vérifier s'il on serialize/clone les éléments ou non.
		if (getCacheConfig(context).shouldSerializeElements()) {
			Assertion.checkArgument(value instanceof Serializable,
					"Object to cache isn't Serializable. Make it Serializable or change its CacheConfig 'serializeElement' parameter. (context: {0}, key:{1}, class:{2})",
					context, key, value.getClass().getSimpleName());
			// Sérialisation avec compression
			final byte[] serializedObject = codecManager.getCompressedSerializationCodec().encode((Serializable) value);
			//La sérialisation est équivalente à un deep Clone.
			putElement(context, key, serializedObject);
		} else {
			//on fait un cache mémoire :
			// - adapté si l'élément est non modifiable
			// - bcp plus performant
			putElement(context, key, value);
		}
	}

	/** {@inheritDoc} */
	@Override
	public Object get(final String context, final Serializable key) {
		final Object cachedObject = getElement(context, key);
		//on ne connait pas l'état Modifiable ou non de l'objet, on se base sur son type.
		if (cachedObject instanceof byte[]) {
			final byte[] serializedObject = (byte[]) cachedObject;
			return codecManager.getCompressedSerializationCodec().decode(serializedObject);
		}
		return cachedObject;
	}

	/** {@inheritDoc} */
	@Override
	public boolean remove(final String context, final Serializable key) {
		return getMapCache(context).remove(key);
	}

	/** {@inheritDoc} */
	@Override
	public synchronized void clearAll() {
		for (final MemoryCache mapCache : cachesPerContext.values()) {
			mapCache.removeAll();
		}
	}

	/** {@inheritDoc} */
	@Override
	public void clear(final String context) {
		//Dans le cas de clear
		final MemoryCache mapCache = cachesPerContext.get(context);
		if (mapCache != null) {
			mapCache.removeAll();
		}
	}

	private void putElement(final String context, final Serializable key, final Object value) {
		getMapCache(context).put(key, value);
	}

	private Object getElement(final String context, final Serializable key) {
		return getMapCache(context).get(key);
	}

	private synchronized MemoryCache getMapCache(final String context) {
		final MemoryCache mapCache = cachesPerContext.get(context);
		Assertion.checkNotNull(mapCache, "Cache {0} are not yet registered.", context);
		return mapCache;
	}

	private synchronized CacheConfig getCacheConfig(final String context) {
		final CacheConfig cacheConfig = cacheConfigsPerContext.get(context);
		Assertion.checkNotNull(cacheConfig, "Cache {0} are not yet registered.", context);
		return cacheConfig;
	}

	/** {@inheritDoc} */
	@Override
	public List<ComponentInfo> getInfos() {
		long hits = 0L;
		long calls = 0L;
		//---
		final List<ComponentInfo> componentInfos = new ArrayList<>();
		for (final String cacheName : cachesPerContext.keySet()) {
			final MemoryCache mapCache = getMapCache(cacheName);
			componentInfos.add(new ComponentInfo("cache." + cacheName + ".elements", mapCache.getElementCount()));
			componentInfos.add(new ComponentInfo("cache." + cacheName + ".hits", mapCache.getHits()));
			componentInfos.add(new ComponentInfo("cache." + cacheName + ".calls", mapCache.getCalls()));
			componentInfos.add(new ComponentInfo("cache." + cacheName + ".ttl", mapCache.getTimeToLiveSeconds()));
			hits += mapCache.getHits();
			calls += mapCache.getCalls();
		}
		final double ratio = 100D * (calls > 0 ? (hits / calls) : 1);//Par convention 100%
		componentInfos.add(new ComponentInfo("cache.hits", hits));
		componentInfos.add(new ComponentInfo("cache.calls", calls));
		componentInfos.add(new ComponentInfo("cache.ratio", ratio));

		return componentInfos;
	}
}
