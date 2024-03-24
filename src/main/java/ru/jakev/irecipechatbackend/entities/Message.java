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
    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    public static class Builder {
        private Conversation conversation;
        private User sender;
        private String text;
        private LocalDateTime sendDate;
        private MessageStatus status;

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

        public Builder setStatus(MessageStatus status) {
            this.status = status;
            return this;
        }

        public Message build() {
            Message message = new Message();
            message.setConversation(this.conversation);
            message.setSender(this.sender);
            message.setText(this.text);
            message.setSendDate(this.sendDate);
            message.setStatus(this.status);
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

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", conversation=" + conversation +
                ", sender=" + sender +
                ", text='" + text + '\'' +
                ", sendDate=" + sendDate +
                ", status=" + status +
                '}';
    }
}
