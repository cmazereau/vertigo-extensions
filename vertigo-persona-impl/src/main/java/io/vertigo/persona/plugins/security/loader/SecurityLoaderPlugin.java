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
package io.vertigo.persona.plugins.security.loader;

import io.vertigo.commons.resource.ResourceManager;
import io.vertigo.core.component.Plugin;
import io.vertigo.core.di.configurator.ResourceConfig;
import io.vertigo.core.lang.Assertion;
import io.vertigo.core.resource.ResourceLoader;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

/**
 * @author pchretien
 */
public final class SecurityLoaderPlugin implements ResourceLoader, Plugin {
	private final ResourceManager resourceManager;

	/**
	 * Constructeur
	 */
	@Inject
	public SecurityLoaderPlugin(final ResourceManager resourceManager) {
		Assertion.checkNotNull(resourceManager);
		//---------------------------------------------------------------------
		this.resourceManager = resourceManager;

	}

	/** {@inheritDoc} */
	public Set<String> getTypes() {
		return Collections.singleton("security");
	}

	/** {@inheritDoc} */
	public void parse(final List<ResourceConfig> resourceConfigs) {
		for (final ResourceConfig resourceConfig : resourceConfigs) {
			final XmlSecurityLoader xmlSecurityLoader = new XmlSecurityLoader(resourceManager, resourceConfig.getPath());
			xmlSecurityLoader.load();
		}
	}
}
