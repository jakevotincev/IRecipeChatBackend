package ru.jakev.irecipechatbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jakev.irecipechatbackend.entities.User;

/**
 * @author evotintsev
 * @since 23.02.2024
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
