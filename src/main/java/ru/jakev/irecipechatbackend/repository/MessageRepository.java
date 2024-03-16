package ru.jakev.irecipechatbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jakev.irecipechatbackend.entities.Message;

/**
 * @author evotintsev
 * @since 28.02.2024
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
}
