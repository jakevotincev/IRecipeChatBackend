package ru.jakev.irecipechatbackend.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import ru.jakev.irecipechatbackend.entities.Conversation;
import ru.jakev.irecipechatbackend.entities.User;
import ru.jakev.irecipechatbackend.services.ConversationService;
import ru.jakev.irecipechatbackend.services.MessageService;
import ru.jakev.irecipechatbackend.websocket.WebSocketMessageSender;
import ru.jakev.irecipechatbackend.websocket.messages.MessageDTO;

/**
 * @author evotintsev
 * @since 26.02.2024
 */
@RestController
@CrossOrigin
public class ChatController {
    private final MessageService messageService;
    private final ConversationService conversationService;
    private final WebSocketMessageSender webSocketMessageSender;

    public ChatController(MessageService messageService, ConversationService conversationService,
                          WebSocketMessageSender webSocketMessageSender) {
        this.messageService = messageService;
        this.conversationService = conversationService;
        this.webSocketMessageSender = webSocketMessageSender;
    }

    //todo: move this code from controller
    @MessageMapping("/message")
    public void sendMessage(MessageDTO message) {
        long senderId = message.getSenderId();
        long recipientId = message.getRecipientId();

        Conversation conversation = conversationService.getConversation(senderId, recipientId)
                .orElseGet(() -> conversationService.createConversation(senderId, recipientId));
        messageService.saveMessage(message, conversation);

        //todo: refactor
        User recipient = conversation.getUsers().stream().filter(user -> user.getId() == recipientId).findFirst().orElse(null);
        if (recipient != null) {
            webSocketMessageSender.sendMessageToUser(recipient.getEmail(), "/specific", message);
        }
    }
}
