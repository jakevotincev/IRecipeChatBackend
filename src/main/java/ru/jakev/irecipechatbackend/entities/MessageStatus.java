package ru.jakev.irecipechatbackend.entities;

/**
 * @author evotintsev
 * @since 24.03.2024
 */
public enum MessageStatus {
    CONFIRMATION("CONFIRMATION"),
    UNREAD("UNREAD"),
    READ("READ");

    private final String status;


    MessageStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
