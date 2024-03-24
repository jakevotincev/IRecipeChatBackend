package ru.jakev.irecipechatbackend.websocket.messages;

import ru.jakev.irecipechatbackend.entities.MessageStatus;

/**
 * @author evotintsev
 * @since 28.02.2024
 */
public class MessageDTO {
    private long senderId;
    private long recipientId;
    private String text;
    private long sendTimestamp;
    private MessageStatus status;
    private final MessageType type = MessageType.MESSAGE;

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getSendTimestamp() {
        return sendTimestamp;
    }

    public void setSendTimestamp(long sendTimestamp) {
        this.sendTimestamp = sendTimestamp;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public MessageType getType() {
        return type;
    }

    public long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(long recipientId) {
        this.recipientId = recipientId;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "senderId=" + senderId +
                ", recipientId=" + recipientId +
                ", text='" + text + '\'' +
                ", sendTimestamp=" + sendTimestamp +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
