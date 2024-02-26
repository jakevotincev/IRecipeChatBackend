package ru.jakev.irecipechatbackend.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author evotintsev
 * @since 26.02.2024
 */
@Controller
public class ChatController {

    @MessageMapping("/chat")
    @SendTo("/chat/messages")
    public String processMessage(String message) {
        return message;
    }
}
