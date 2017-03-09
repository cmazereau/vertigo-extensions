package io.vertigo.commons.impl.analytics;

import io.vertigo.commons.analytics.AnalyticsTracer;

/**
 * Dummy implementation of a tracer.
 * Used when collect is disabled.
 * @author mlaroche
 *
 */
final class AnalyticsTracerDummy implements AnalyticsTracer {

	static final AnalyticsTracer DUMMY_TRACER = new AnalyticsTracerDummy();

	private AnalyticsTracerDummy() {
	}

	/** {@inheritDoc} */
	@Override
	public AnalyticsTracer incMeasure(final String name, final double value) {
		return this;
	}

	/** {@inheritDoc} */
	@Override
	public AnalyticsTracer setMeasure(final String name, final double value) {
		return this;
	}

	/** {@inheritDoc} */
	@Override
	public AnalyticsTracer addTag(final String name, final String value) {
		return this;
	}

}
