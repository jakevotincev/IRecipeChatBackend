package ru.jakev.irecipechatbackend.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * @author evotintsev
 * @since 16.03.2024
 */
public class DefaultWebSocketMessageSender implements WebSocketMessageSender {
    private final Logger LOG = LoggerFactory.getLogger(DefaultWebSocketMessageSender.class);
    private final SimpMessagingTemplate simpMessagingTemplate;

    public DefaultWebSocketMessageSender(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public void sendMessageToUser(String username, String destination, Object message) {
        simpMessagingTemplate.convertAndSendToUser(username, destination, message);
        //todo: maybe delete this log
        LOG.info("Send message {} to {}, user: {}", message, destination, username);
    }
}
