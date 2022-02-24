package com.richard.brewer.repository.help.style;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.richard.brewer.model.Style;
import com.richard.brewer.repository.filter.StyleFilter;

public interface StylesQueries {
	
	public Page<Style> filter(StyleFilter styleFilter, Pageable pageable);

}
