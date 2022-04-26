package com.richard.ms1.infrastructure.persistence.repositories;

import com.richard.ms1.infrastructure.persistence.entities.BaseDataOne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BaseDataOneRepository extends JpaRepository<BaseDataOne, Long> {

    Optional<BaseDataOne> findByNome(String nome);

}
