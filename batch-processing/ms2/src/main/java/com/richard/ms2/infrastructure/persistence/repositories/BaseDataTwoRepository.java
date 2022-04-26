package com.richard.ms2.infrastructure.persistence.repositories;

import com.richard.ms2.infrastructure.persistence.entities.BaseDataTwo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BaseDataTwoRepository extends JpaRepository<BaseDataTwo, Long> {

    Optional<BaseDataTwo> findByNome(String nome);

}
