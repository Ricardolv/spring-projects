package com.richard.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.richard.brewer.model.State;

@Repository
public interface States extends JpaRepository<State, Long> {
	
	Optional<State> findByNameIgnoreCase(String name);

}
