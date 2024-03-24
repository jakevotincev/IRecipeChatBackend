package ru.jakev.irecipechatbackend.websocket.messages;

/**
 * @author evotintsev
 * @since 24.03.2024
 */
public enum MessageType {
    MESSAGE("MESSAGE"),
    MESSAGE_SENT("MESSAGE_SENT");

    private final String type;

    MessageType(String type) {
        this.type = type;
    }
}
