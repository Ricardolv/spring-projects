package com.richard.tamingthymeleaf.infrastructure.resource.user.validator.annotation;

import com.richard.tamingthymeleaf.infrastructure.resource.user.validator.impl.PasswordsMatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordsMatchValidator.class)
public @interface PasswordsMatch {

    String message() default "{PasswordsNotMatching}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
