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
package io.vertigo.social.plugins.comment.memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import io.vertigo.dynamo.domain.model.KeyConcept;
import io.vertigo.dynamo.domain.model.UID;
import io.vertigo.social.impl.comment.CommentPlugin;
import io.vertigo.social.services.comment.Comment;

/**
 * @author pchretien
 */
public final class MemoryCommentPlugin implements CommentPlugin {
	private final Map<UUID, Comment> map = new HashMap<>();
	private final Map<UID, List<UUID>> commentsMap = new HashMap<>();

	/** {@inheritDoc} */
	@Override
	public synchronized <S extends KeyConcept> void publish(final Comment comment, final UID<S> uid) {
		final List<UUID> comments = commentsMap.getOrDefault(uid, new ArrayList<>());
		comments.add(0, comment.getUuid());
		commentsMap.put(uid, comments);
		map.put(comment.getUuid(), comment);
	}

	/** {@inheritDoc} */
	@Override
	public synchronized void update(final Comment comment) {
		map.put(comment.getUuid(), comment);
	}

	/** {@inheritDoc} */
	@Override
	public synchronized Comment get(final UUID uuid) {
		return map.get(uuid);
	}

	/** {@inheritDoc} */
	@Override
	public synchronized <S extends KeyConcept> List<Comment> getComments(final UID<S> uid) {
		return commentsMap.getOrDefault(uid, Collections.emptyList())
				.stream()
				.map(uuid -> get(uuid))
				.collect(Collectors.toList());
	}
}
