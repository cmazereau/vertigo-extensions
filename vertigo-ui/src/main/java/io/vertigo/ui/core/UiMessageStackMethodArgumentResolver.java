package io.vertigo.ui.core;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import io.vertigo.lang.Assertion;
import io.vertigo.vega.webservice.validation.UiMessageStack;

public class UiMessageStackMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(final MethodParameter parameter) {
		return UiMessageStack.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer, final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) throws Exception {
		final RequestAttributes attributes = RequestContextHolder.currentRequestAttributes();
		final UiMessageStack uiMessageStack = (UiMessageStack) attributes.getAttribute("uiMessageStack", RequestAttributes.SCOPE_REQUEST);
		Assertion.checkNotNull(uiMessageStack);
		//---
		return uiMessageStack;
	}

}
