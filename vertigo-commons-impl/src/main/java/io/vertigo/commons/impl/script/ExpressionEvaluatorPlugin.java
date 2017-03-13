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
package io.vertigo.commons.impl.script;

import java.util.List;

import io.vertigo.commons.script.ExpressionParameter;
import io.vertigo.lang.Plugin;

/**
 * Evaluation d'une expression.
 * 
 * @author  pchretien
 */
public interface ExpressionEvaluatorPlugin extends Plugin {
	/**
	 * Evaluation d'une expression.
	 * @param expression Expression
	 * @param parameters Paramètres
	 * @param type Type retourné
	 * @return Résultat de l'expression après évaluation
	 */
	<J> J evaluate(final String expression, List<ExpressionParameter> parameters, Class<J> type);
}
