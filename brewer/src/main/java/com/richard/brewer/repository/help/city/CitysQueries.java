package com.richard.brewer.repository.help.city;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.richard.brewer.model.City;
import com.richard.brewer.repository.filter.CityFilter;

public interface CitysQueries {
	
	public Page<City> filter(CityFilter filter, Pageable pageable);
	public City findOfState(Long code);

}
