package com.richard.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.richard.brewer.model.Beer;
import com.richard.brewer.repository.help.beer.BeersQueries;

@Repository
public interface Beers extends JpaRepository<Beer, Long>, BeersQueries {
	
	public Optional<Beer> findBySkuIgnoreCase(String sku);
	
}
