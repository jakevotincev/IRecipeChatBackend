package ru.jakev.irecipechatbackend.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.jakev.irecipechatbackend.entities.User;
import ru.jakev.irecipechatbackend.repository.UserRepository;
import ru.jakev.irecipechatbackend.services.UserService;

import java.util.Optional;

/**
 * @author evotintsev
 * @since 23.02.2024
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //todo: add controller advise to handle exceptions
    @Override
    public boolean saveUser(User user) {
        try {
            //todo: add check username?
            getUserByEmail(user.getEmail()).ifPresent(u -> {
                throw new IllegalArgumentException(String.format("User with email=%s already exists", user.getEmail()));
            });
        } catch (Exception e) {
            LOG.error("Error while saving user", e);
            return false;
        }

        userRepository.save(user);
        return true;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return Optional.empty();
        }

        return Optional.of(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public boolean isUserExists(long id) {
        return userRepository.existsById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getUserByEmail(email).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with email=%s not found", email));
        }

        return user;
    }
}
