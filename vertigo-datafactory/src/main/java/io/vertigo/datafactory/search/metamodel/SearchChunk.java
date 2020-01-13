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
package io.vertigo.datafactory.search.metamodel;

import java.util.Collections;
import java.util.List;

import io.vertigo.core.lang.Assertion;
import io.vertigo.dynamo.domain.model.KeyConcept;
import io.vertigo.dynamo.domain.model.UID;

/**
 * Chunk of keyConcept uris.
 * @author npiedeloup, pchretien
 * @param <K> the type of the KeyConcept
 */
public final class SearchChunk<K extends KeyConcept> {
	private final List<UID<K>> uids;

	/**
	 * @param uids the list of keyConcept uids
	 */
	public SearchChunk(final List<UID<K>> uids) {
		Assertion.checkNotNull(uids);
		//---
		this.uids = Collections.unmodifiableList(uids); // pas de clone pour l'instant
	}

	/**
	 * @return All KeyConcept's uids of this chunk
	 */
	public List<UID<K>> getAllUIDs() {
		return uids;
	}

	public UID getLastUID() {
		return uids.get(uids.size() - 1);
	}
}