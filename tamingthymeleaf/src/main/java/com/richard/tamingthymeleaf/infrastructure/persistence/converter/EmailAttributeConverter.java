package com.richard.tamingthymeleaf.infrastructure.persistence.converter;

import com.richard.tamingthymeleaf.infrastructure.persistence.user.Email;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EmailAttributeConverter implements AttributeConverter<Email, String> {

    @Override
    public String convertToDatabaseColumn(Email attribute) {
        return attribute.asString();
    }

    @Override
    public Email convertToEntityAttribute(String dbData) {
        return new Email(dbData);
    }

}
