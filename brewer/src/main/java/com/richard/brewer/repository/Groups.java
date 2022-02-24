package com.richard.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.richard.brewer.model.Group;

@Repository
public interface Groups extends JpaRepository<Group, Long> {

}
