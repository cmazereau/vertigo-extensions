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
package io.vertigo.social.notification.webservices;

import javax.inject.Inject;

import io.vertigo.account.authentication.AuthenticationManager;
import io.vertigo.account.impl.authentication.UsernameAuthenticationToken;
import io.vertigo.account.security.VSecurityManager;
import io.vertigo.vega.webservice.WebServices;
import io.vertigo.vega.webservice.stereotype.AnonymousAccessAllowed;
import io.vertigo.vega.webservice.stereotype.GET;
import io.vertigo.vega.webservice.stereotype.PathPrefix;
import io.vertigo.vega.webservice.stereotype.QueryParam;
import io.vertigo.vega.webservice.stereotype.SessionInvalidate;

//basé sur http://www.restapitutorial.com/lessons/httpmethods.html

@PathPrefix("/test")
public final class TestLoginWebServices implements WebServices {

	@Inject
	private VSecurityManager securityManager;
	@Inject
	private AuthenticationManager authenticationManager;

	@AnonymousAccessAllowed
	@GET("/login")
	public void login(@QueryParam("id") final String id) {
		//code 200
		securityManager.getCurrentUserSession().get().authenticate();
		authenticationManager.login(new UsernameAuthenticationToken(id));
	}

	@SessionInvalidate
	@GET("/logout")
	public void logout() {
		//code 200
	}

}
