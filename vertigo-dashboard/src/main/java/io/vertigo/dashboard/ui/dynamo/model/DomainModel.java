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
package io.vertigo.dashboard.ui.dynamo.model;

import io.vertigo.dynamo.domain.metamodel.Domain;
import io.vertigo.lang.Assertion;

public class DomainModel {
	private final Domain domainDefinition;
	private final Double taskCount; // may be null for UI (displayed as N/A)
	private final Double dtDefinitionCount; // may be null for UI (displayed as N/A)

	public DomainModel(final Domain domainDefinition, final Double taskCount, final Double dtDefinitionCount) {
		Assertion.checkNotNull(domainDefinition);
		//---
		this.domainDefinition = domainDefinition;
		this.taskCount = taskCount;
		this.dtDefinitionCount = dtDefinitionCount;
	}

	public String getName() {
		return domainDefinition.getName();
	}

	public boolean isOrphan() {
		if (taskCount != null && dtDefinitionCount != null) {
			return taskCount + dtDefinitionCount == 0;
		} else if (taskCount != null) {// dtDefinitionCount null
			return taskCount == 0;
		}
		// both nulls
		return false;
	}

	public Double getTaskCount() {
		return taskCount;
	}

	public Double getDtDefinitionCount() {
		return dtDefinitionCount;
	}

	public Double getUsageCount() {
		return taskCount + dtDefinitionCount;
	}

}