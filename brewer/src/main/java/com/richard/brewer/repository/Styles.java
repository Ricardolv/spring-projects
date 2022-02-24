package com.richard.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.richard.brewer.model.Style;
import com.richard.brewer.repository.help.style.StylesQueries;

@Repository
public interface Styles extends JpaRepository<Style, Long>, StylesQueries {
	
	Optional<Style> findByNameIgnoreCase(String name);
	
}
