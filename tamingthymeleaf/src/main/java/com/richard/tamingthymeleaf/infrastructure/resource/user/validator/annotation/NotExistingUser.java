package com.richard.tamingthymeleaf.infrastructure.resource.user.validator.annotation;

import com.richard.tamingthymeleaf.infrastructure.resource.user.validator.impl.NotExistingUserValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) //<.>
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotExistingUserValidator.class) //<.>
public @interface NotExistingUser {
    String message() default "{UserAlreadyExisting}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
