package io.vertigo.dynamo.ngdomain.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface Formatter {
	Class<? extends io.vertigo.dynamo.domain.metamodel.Formatter> clazz();

	String arg() default "";

}