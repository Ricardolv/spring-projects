package com.richard.brewer.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.richard.brewer.model.User;
import com.richard.brewer.repository.Users;
import com.richard.brewer.repository.filter.UserFilter;
import com.richard.brewer.service.exception.ImpossibleDeleteEntityException;
import com.richard.brewer.service.exception.UserEmailExistsException;
import com.richard.brewer.service.exception.UserPasswordRequiredException;

@Service
public class UsersService {
	
	@Autowired
	private Users users;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public void save(User user) {
		
		Optional<User> userExist = users.findByEmail(user.getEmail());
		
		emailValid(user, userExist);
		passwordValid(user, userExist);
		statusSetting(user, userExist);
		
		user.setPasswordConfirm(user.getPassword());
		
		users.save(user);
	}

	private void emailValid(User user, Optional<User> userExist) {
		if (userExist.isPresent() && !user.equals(userExist.get())) {
			throw new UserEmailExistsException("E-mail já cadastrado");
		}
	}

	private void passwordValid(User user, Optional<User> userExist) {
		if (user.isNew() && StringUtils.isEmpty(user.getPassword())) {
			throw new UserPasswordRequiredException("Senha é obrigatório para novo usuário");
		}
		
		if (user.isNew() || !StringUtils.isEmpty(user.getPassword())) {
			user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		} else if (StringUtils.isEmpty(user.getPassword())) {
			user.setPassword(userExist.get().getPassword());
		}
	}

	private void statusSetting(User user, Optional<User> userExist) {
		if (user.isEdit() && null == user.getActive()) {
			user.setActive(userExist.get().getActive());
		}
	}

	public List<User> findAll() {
		return users.findAll();
	}

	@Transactional
	public void statusAlter(Long[] codes, StatusUser status) {
		status.execute(codes, users);
	}
	
	@Transactional
	public void delete(User user) {
		try {
			users.delete(user);
			users.flush();
		} catch (PersistenceException e) {
			throw new ImpossibleDeleteEntityException("Impossível apagar usuário.");
		}
		
	}

	public Page<User> filter(UserFilter userFilter, Pageable pageable) {
		return users.filter(userFilter, pageable);
	}

	public User findOfGroups(Long code) {
		return users.findOfGroups(code);
	}

}
