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
package io.vertigo.ui.impl.springmvc.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.RequestToViewNameTranslator;

import io.vertigo.lang.Assertion;
import io.vertigo.ui.impl.springmvc.controller.AbstractVSpringMvcController;

public class VRequestToViewNameTranslator implements RequestToViewNameTranslator {

	@Override
	public String getViewName(final HttpServletRequest request) throws Exception {
		final String defaultViewName = (String) request.getAttribute(AbstractVSpringMvcController.DEFAULT_VIEW_NAME_ATTRIBUTE);
		Assertion.checkNotNull(defaultViewName);
		//---
		return defaultViewName;
	}

}
