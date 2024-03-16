package ru.jakev.irecipechatbackend.websocket;

/**
 * @author evotintsev
 * @since 16.03.2024
 */
public interface WebSocketMessageSender {
    void sendMessageToUser(String username, String destination, Object message);
}
