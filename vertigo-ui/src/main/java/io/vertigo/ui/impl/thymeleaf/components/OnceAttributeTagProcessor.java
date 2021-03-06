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
package io.vertigo.ui.impl.thymeleaf.components;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.expression.Ids;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.standard.processor.AbstractStandardConditionalVisibilityTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

public class OnceAttributeTagProcessor extends AbstractStandardConditionalVisibilityTagProcessor {

	public static final int PRECEDENCE = 300;
	public static final String ATTR_NAME = "once";

	/**
	 * Constructor.
	 * @param dialectPrefix Dialect prefix (tc)
	 */
	public OnceAttributeTagProcessor(final String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, ATTR_NAME, PRECEDENCE);
	}

	@Override
	protected boolean isVisible(final ITemplateContext context,
			final IProcessableElementTag tag, final AttributeName attributeName,
			final String attributeValue) {
		final Ids ids = new Ids(context);
		final String id = ids.seq(attributeValue);
		return (attributeValue + "1").equals(id);
	}
}
