package com.richard.brewer.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.richard.brewer.model.Group;

@Component
public class GroupConverter implements Converter<String, Group> {

	@Override
	public Group convert(String code) {

		if (!StringUtils.isEmpty(code)) {
			Group group = new Group();
			group.setCode(Long.valueOf(code));
			return group;
		}
		
		return null;
	}

}
