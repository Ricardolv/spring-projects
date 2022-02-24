package com.richard.brewer.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.richard.brewer.model.Style;

@Component
public class StyleConverter implements Converter<String, Style> {

	@Override
	public Style convert(String code) {
		
		if (!StringUtils.isEmpty(code)) {
			Style style = new Style();
			style.setCode(Long.valueOf(code));
			return style;
		}
		return null;
	}

}
