package com.richard.brewer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.richard.brewer.model.User;
import com.richard.brewer.repository.help.user.UsersQueries;

@Repository
public interface Users extends JpaRepository<User, Long>, UsersQueries {

	Optional<User> findByEmail(String email);

	List<User> findByCodeIn(Long[] codes);

}
