/**
 * vertigo - simple java starter
 *
 * Copyright (C) 2013-2017, KleeGroup, direction.technique@kleegroup.com (http://www.kleegroup.com)
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
package io.vertigo.stella.work;

import io.vertigo.app.Home;
import io.vertigo.core.component.di.injector.DIInjector;
import io.vertigo.lang.Assertion;
import io.vertigo.util.ClassUtil;

/**
 * Provider des taches.
 * Ce provider définit le moyen dont la tache doit être exécuter.
 * Dans la plupart des cas le moyen est une classe.
 * Dans certain cs il peut s'agir du nom de la classe.
 * @author  pchretien
 * @param <R> result
 * @param <W> work
 */
public final class WorkEngineProvider<R, W> {
	private final String className;
	private final Class<? extends WorkEngine<R, W>> clazz;
	private final WorkEngine<R, W> workEngine;

	/**
	 * Constructor.
	 * @param clazz Workengine class
	 */
	public WorkEngineProvider(final Class<? extends WorkEngine<R, W>> clazz) {
		Assertion.checkNotNull(clazz);
		//-----
		this.clazz = clazz;
		this.className = clazz.getName();
		this.workEngine = null;
	}

	/**
	 * Constructor.
	 * @param workEngine Workengine instance
	 */
	public WorkEngineProvider(final WorkEngine<R, W> workEngine) {
		Assertion.checkNotNull(workEngine);
		//-----
		this.workEngine = workEngine;
		this.clazz = null;
		this.className = workEngine.getClass().getName();
	}

	/**
	 * Constructor.
	 * @param className Workengine class name
	 */
	public WorkEngineProvider(final String className) {
		Assertion.checkArgNotEmpty(className);
		//-----
		this.className = className;
		this.clazz = null;
		this.workEngine = null;
	}

	/**
	 * @return WorkEngine
	 */
	public WorkEngine<R, W> provide() {
		if (workEngine != null) {
			return workEngine;
		}
		final Class<? extends WorkEngine<R, W>> engineClazz;
		if (clazz != null) {
			engineClazz = clazz;
		} else {
			engineClazz = (Class<? extends WorkEngine<R, W>>) ClassUtil.classForName(className);
		}
		//récupéartion de l'engine par sa classe.
		return DIInjector.newInstance(engineClazz, Home.getApp().getComponentSpace());
	}

	/**
	 * @return WorkEngine name
	 */
	public String getName() {
		return className;
	}

}