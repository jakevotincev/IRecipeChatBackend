package ru.jakev.irecipechatbackend.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * @author evotintsev
 * @since 28.02.2024
 */
@Entity
public class Message {
    @Id
    @GeneratedValue
    private Long id;
    @Nonnull
    @ManyToOne
    @JoinColumn(name = "conversation_id", referencedColumnName = "id")
    private Conversation conversation;
    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    //todo: cascade type
    private User sender;
    @Nonnull
    private String text;
    @Nonnull
    private LocalDateTime sendDate;
    @Nonnull
    private Boolean isRead;

    public static class Builder {
        private Conversation conversation;
        private User sender;
        private String text;
        private LocalDateTime sendDate;
        private Boolean isRead;

        public Builder setConversation(Conversation conversation) {
            this.conversation = conversation;
            return this;
        }

        public Builder setSender(User sender) {
            this.sender = sender;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setSendDate(LocalDateTime sendDate) {
            this.sendDate = sendDate;
            return this;
        }

        public Builder setRead(Boolean read) {
            isRead = read;
            return this;
        }

        public Message build() {
            Message message = new Message();
            message.setConversation(this.conversation);
            message.setSender(this.sender);
            message.setText(this.text);
            message.setSendDate(this.sendDate);
            message.setRead(this.isRead);
            return message;
        }
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", conversation=" + conversation +
                ", sender=" + sender +
                ", text='" + text + '\'' +
                ", sendDate=" + sendDate +
                ", isRead=" + isRead +
                '}';
    }
}
