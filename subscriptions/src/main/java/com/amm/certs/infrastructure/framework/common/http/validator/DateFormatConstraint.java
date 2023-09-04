package com.amm.certs.infrastructure.framework.common.http.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateFormatValidator.class)
public @interface DateFormatConstraint {

    String message() default "Invalid date format";

    String pattern();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
