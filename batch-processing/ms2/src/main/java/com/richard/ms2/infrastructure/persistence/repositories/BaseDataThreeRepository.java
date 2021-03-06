package com.richard.ms2.infrastructure.persistence.repositories;

import com.richard.ms2.infrastructure.persistence.entities.BaseDataThree;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BaseDataThreeRepository extends JpaRepository<BaseDataThree, Long> {

    Optional<BaseDataThree> findByEmailAndSenha(String email, String senha);
}
