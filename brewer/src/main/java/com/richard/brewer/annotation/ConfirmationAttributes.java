package com.richard.brewer.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

import com.richard.brewer.annotation.validator.AttributeConfirmValidator;

@Target({ ElementType.TYPE })
@Retention( RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { AttributeConfirmValidator.class })
public @interface ConfirmationAttributes {
	
	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "Atributos não confere";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	String attribute();
	String attributeConfirm();
}
