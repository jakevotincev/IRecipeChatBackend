package ru.jakev.irecipechatbackend.websocket.messages;

import java.time.LocalDateTime;

/**
 * @author evotintsev
 * @since 28.02.2024
 */
public class MessageDTO {
    private long senderId;
    private long recipientId;
    private String text;
    private long sendTimestamp;
    private boolean isRead;

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

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
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
                ", isRead=" + isRead +
                '}';
    }
}
