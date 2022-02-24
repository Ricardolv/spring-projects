package com.richard.brewer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.richard.brewer.model.City;
import com.richard.brewer.model.State;
import com.richard.brewer.repository.help.city.CitysQueries;

@Repository
public interface Citys extends JpaRepository<City, Long>, CitysQueries {
	
	public List<City> findByStateCode(Long codeState);
	public Optional<City> findByStateAndName(State state, String name);

}
