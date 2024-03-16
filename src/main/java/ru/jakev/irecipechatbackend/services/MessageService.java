package ru.jakev.irecipechatbackend.services;

import ru.jakev.irecipechatbackend.entities.Conversation;
import ru.jakev.irecipechatbackend.entities.Message;
import ru.jakev.irecipechatbackend.websocket.messages.MessageDTO;

/**
 * @author evotintsev
 * @since 28.02.2024
 */
public interface MessageService {
    Message saveMessage(MessageDTO message, Conversation conversation);
}
