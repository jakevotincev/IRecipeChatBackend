package ru.jakev.irecipechatbackend.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author evotintsev
 * @since 28.02.2024
 */
@Entity
public class Conversation {
    @Id
    @GeneratedValue
    @Nonnull
    private Long id;
    @Nonnull
    private LocalDateTime creationDate;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_conversation",
            joinColumns = @JoinColumn(name = "conversation_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    //todo: fetch type?
    //todo: make immutable
    private Set<User> users;
    @OneToMany(mappedBy = "conversation")
    private Set<Message> messages;

    public static class Builder {
        private LocalDateTime creationDate;
        private Set<User> users;
        private Set<Message> messages;
        public Builder setCreationDate(LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Builder withUsers(Set<User> users) {
            this.users = users;
            return this;
        }

        public Conversation build() {
            Conversation conversation = new Conversation();
            conversation.setCreationDate(this.creationDate);
            conversation.setUsers(this.users);
            conversation.setMessages(this.messages);
            return conversation;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", users=" + users +
                ", messages=" + messages +
                '}';
    }
}
