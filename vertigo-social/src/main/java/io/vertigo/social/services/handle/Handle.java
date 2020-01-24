package io.vertigo.social.services.handle;

import io.vertigo.core.lang.Assertion;
import io.vertigo.datamodel.structure.model.UID;

public final class Handle {

	private final UID uid;
	private final String code;

	public Handle(
			final UID uid,
			final String code) {
		Assertion.checkNotNull(uid);
		Assertion.checkArgNotEmpty(code);
		//---
		this.uid = uid;
		this.code = code;
	}

	public UID getUid() {
		return uid;
	}

	public String getCode() {
		return code;
	}

}
