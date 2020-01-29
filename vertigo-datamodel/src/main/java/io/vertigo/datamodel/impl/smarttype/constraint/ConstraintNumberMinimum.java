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
package io.vertigo.datamodel.impl.smarttype.constraint;

import java.util.Optional;

import io.vertigo.core.lang.Assertion;
import io.vertigo.core.locale.MessageText;
import io.vertigo.datamodel.structure.metamodel.Constraint;
import io.vertigo.datamodel.structure.metamodel.DtProperty;
import io.vertigo.datamodel.structure.metamodel.Property;

/**
 * Contrainte sur la valeur minimale d'un nombre.
 * arguments = valeur minimale.
 * @author npiedeloup
 */
public final class ConstraintNumberMinimum implements Constraint<Number, Number> {
	private final double minValue;
	private final MessageText errorMessage;

	/**
	 * Constructor.
	 * @param args the minimum value
	 */
	public ConstraintNumberMinimum(final String args, final Optional<String> overrideMessageOpt) {
		Assertion.checkArgument(args != null && args.length() > 0, "Vous devez préciser la valeur minimum comme argument de ConstraintNumberMinimum");
		Assertion.checkNotNull(overrideMessageOpt);
		//-----
		minValue = Double.parseDouble(args);
		errorMessage = overrideMessageOpt.isPresent() ? MessageText.of(overrideMessageOpt.get()) : MessageText.of(Resources.DYNAMO_CONSTRAINT_NUMBER_MINIMUM, minValue);
	}

	/** {@inheritDoc} */
	@Override
	public boolean checkConstraint(final Number value) {
		if (value == null) {
			return true;
		}
		return value.doubleValue() >= minValue;
	}

	/** {@inheritDoc} */
	@Override
	public MessageText getErrorMessage() {
		return errorMessage;
	}

	/** {@inheritDoc} */
	@Override
	public Property getProperty() {
		return DtProperty.MIN_VALUE;
	}

	/** {@inheritDoc} */
	@Override
	public Number getPropertyValue() {
		return minValue;
	}
}