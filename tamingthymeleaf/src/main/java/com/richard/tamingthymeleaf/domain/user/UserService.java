package com.richard.tamingthymeleaf.domain.user;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.richard.tamingthymeleaf.application.exceptions.BusinessException;
import com.richard.tamingthymeleaf.application.exceptions.NotFoundException;
import com.richard.tamingthymeleaf.infrastructure.persistence.user.Email;
import com.richard.tamingthymeleaf.infrastructure.persistence.user.User;
import com.richard.tamingthymeleaf.infrastructure.persistence.user.UserId;
import com.richard.tamingthymeleaf.infrastructure.persistence.user.UserRepository;
import com.richard.tamingthymeleaf.infrastructure.resource.user.UserNameAndId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@Transactional
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(CreateUserParameters parameters) {
        LOGGER.debug("Creating user {} ({})", parameters.getUserName().getFullName(), parameters.getEmail().asString());

        UserId userId = repository.nextId();
        String encodedPassword = passwordEncoder.encode(parameters.getPassword());
        User user = User.createUser(userId,
                parameters.getUserName(),
                encodedPassword,
                parameters.getGender(),
                parameters.getBirthday(),
                parameters.getEmail(),
                parameters.getPhoneNumber());
        storeAvatarIfPresent(parameters, user);
        return repository.save(user);
    }

    public User createAdministrator(CreateUserParameters parameters) {
        LOGGER.debug("Creating administrator {} ({})", parameters.getUserName().getFullName(), parameters.getEmail().asString());

        UserId userId = repository.nextId();
        User user = User.createAdministrator(userId,
                parameters.getUserName(),
                passwordEncoder.encode(parameters.getPassword()),
                parameters.getGender(),
                parameters.getBirthday(),
                parameters.getEmail(),
                parameters.getPhoneNumber());

        storeAvatarIfPresent(parameters, user);
        return repository.save(user);
    }

    public User editUser(UserId userId, EditUserParameters parameters) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s not found", userId.asString())));

        if (parameters.getVersion() != user.getVersion()) {
            throw new ObjectOptimisticLockingFailureException(User.class, user.getId().asString());
        }

        parameters.update(user);
        return user;
    }

    @Transactional(readOnly = true)
    public Page<User> getUsers(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public ImmutableSet<User> getAllUsers() {
        return ImmutableSet.copyOf(repository.findAll());
    }

    @Transactional(readOnly = true)
    public boolean userWithEmailExists(Email email) {
        return repository.existsByEmail(email);
    }


    public Optional<User> getUser(UserId userId) {
        return repository.findById(userId);
    }

    public void deleteUser(UserId userId) {
        repository.deleteById(userId);
    }

    public Long countUsers() {
        return repository.count();
    }

    public void deleteAllUsers() {
        repository.deleteAll();
    }

    public ImmutableSortedSet<UserNameAndId> getAllUsersNameAndId() {
        Iterable<User> users = repository.findAll();
        return ImmutableSortedSet.copyOf(
                Comparator.comparing(userNameAndId ->
                        userNameAndId.getUserName().getFullName()),
                StreamSupport.stream(users.spliterator(), false)
                        .map(user -> new UserNameAndId(user.getId(),
                                user.getUserName()))
                        .sorted(Comparator.comparing(userNameAndId ->
                                userNameAndId.getUserName().getFullName()))
                        .collect(Collectors.toList()));
    }

    private void storeAvatarIfPresent(CreateUserParameters parameters, User user) {
        MultipartFile avatar = parameters.getAvatar();
        if (avatar != null) {
            try {
                user.setAvatar(avatar.getBytes());
            } catch (IOException e) {
                throw new BusinessException(e);
            }
        }
    }

}
