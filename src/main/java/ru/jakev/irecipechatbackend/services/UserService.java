package ru.jakev.irecipechatbackend.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.jakev.irecipechatbackend.entities.User;

import java.util.Optional;

/**
 * @author evotintsev
 * @since 23.02.2024
 */
public interface UserService extends UserDetailsService {
    boolean saveUser(User user);
    Optional<User> getUserByEmail(String username);
}
