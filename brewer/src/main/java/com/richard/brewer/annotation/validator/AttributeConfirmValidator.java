package com.richard.brewer.annotation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.apache.commons.beanutils.BeanUtils;

import com.richard.brewer.annotation.ConfirmationAttributes;

public class AttributeConfirmValidator implements ConstraintValidator<ConfirmationAttributes, Object> {

	private String attribute;
	private String attributeConfirm;
	
	@Override
	public void initialize(ConfirmationAttributes constraintAnnotation) {
		this.attribute = constraintAnnotation.attribute();
		this.attributeConfirm = constraintAnnotation.attributeConfirm();
		
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		boolean _return = false;
		try {
			
			Object valueAttribute = BeanUtils.getProperty(object, this.attribute);
			Object valueAttributeConfirm = BeanUtils.getProperty(object, this.attributeConfirm);
			
			_return = isBothNulls(valueAttribute, valueAttributeConfirm) || isBothEquals(valueAttribute, valueAttributeConfirm);
			
		} catch (Exception e) {
			throw new RuntimeException("Erro recuperando valores dos atributos", e);
		} 
		
		if (!_return) {
			context.disableDefaultConstraintViolation();
			String message = context.getDefaultConstraintMessageTemplate();
			ConstraintViolationBuilder violationBuilder = context.buildConstraintViolationWithTemplate(message);
			violationBuilder.addPropertyNode(this.attributeConfirm).addConstraintViolation();
		}
		
		
		return _return;
	}

	private boolean isBothEquals(Object valueAttribute, Object valueAttributeConfirm) {
		return null != valueAttribute && valueAttribute.equals(valueAttributeConfirm) ;
	}

	private boolean isBothNulls(Object valueAttribute, Object valueAttributeConfirm) {
		return null == valueAttribute && null == valueAttributeConfirm;
	}

}
