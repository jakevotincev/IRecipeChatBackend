package ru.jakev.irecipechatbackend.services.impl;

import org.springframework.stereotype.Service;
import ru.jakev.irecipechatbackend.entities.Conversation;
import ru.jakev.irecipechatbackend.entities.Message;
import ru.jakev.irecipechatbackend.mappers.MessageMapper;
import ru.jakev.irecipechatbackend.repository.MessageRepository;
import ru.jakev.irecipechatbackend.services.ConversationService;
import ru.jakev.irecipechatbackend.services.MessageService;
import ru.jakev.irecipechatbackend.websocket.messages.MessageDTO;

/**
 * @author evotintsev
 * @since 28.02.2024
 */
@Service
public class MessageServiceImpl implements MessageService {
    private final ConversationService conversationService;
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    public MessageServiceImpl(ConversationService conversationService, MessageRepository messageRepository,
                              MessageMapper messageMapper) {
        this.conversationService = conversationService;
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    @Override
    public Message saveMessage(MessageDTO messageDTO, Conversation conversation) {
        conversationService.getConversationById(conversation.getId())
                .orElseThrow(() -> new IllegalArgumentException("Conversation not found"));

        Message message = messageMapper.toMessage(messageDTO);
        message.setConversation(conversation);
        return messageRepository.save(message);
    }
}
