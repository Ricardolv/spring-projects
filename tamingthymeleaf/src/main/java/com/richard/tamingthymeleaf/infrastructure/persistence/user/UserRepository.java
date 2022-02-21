package com.richard.tamingthymeleaf.infrastructure.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, UserId>, UserRepositoryCustom {

    boolean existsByEmail(Email email);
    Optional<User> findByEmail(Email email);

}