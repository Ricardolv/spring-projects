package com.richard.brewer.controller.validator;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.richard.brewer.model.Sale;

@Component
public class SaleValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Sale.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "client.code", "", "Selecione um cliente na pesquisa rápida");
		
		Sale sale = (Sale) target;
		
		validateIfOnlyInformedDeliveryHour(errors, sale);
		validatorInformatedItems(errors, sale);
		validatorTotalValueNegative(errors, sale);
	}

	private void validatorTotalValueNegative(Errors errors, Sale sale) {
		if (sale.getTotalValue().compareTo(BigDecimal.ZERO) < 0) {
			errors.reject("", "Valor total nao pode ser negativo");
		}
	}

	private void validatorInformatedItems(Errors errors, Sale sale) {
		if (sale.getItems().isEmpty()) {
			errors.reject("", "Adicione pelo menos uma cerveja na venda");
		}
	}

	private void validateIfOnlyInformedDeliveryHour(Errors errors, Sale sale) {
		if (null != sale.getDeliveryHour() && null == sale.getDeliveryDate()) {
			errors.rejectValue("deliveryDate", "", "Informe uma data da entrega para um horário");
		}
	}

}
