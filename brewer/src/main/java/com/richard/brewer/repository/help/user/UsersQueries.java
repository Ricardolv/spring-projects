package com.richard.brewer.repository.help.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.richard.brewer.model.User;
import com.richard.brewer.repository.filter.UserFilter;

public interface UsersQueries {
	
	public Optional<User> findByEmailAndActive(String email);
	
	public List<String> permissions(User user);
	
	public Page<User> filter(UserFilter userFilter, Pageable pageable);
	
	public User findOfGroups(Long code);
}
