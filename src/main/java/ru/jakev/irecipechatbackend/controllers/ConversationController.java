package ru.jakev.irecipechatbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jakev.irecipechatbackend.http.responses.ConversationDTO;
import ru.jakev.irecipechatbackend.services.ConversationService;

import java.util.List;

/**
 * @author evotintsev
 * @since 08.03.2024
 */
@RestController
@RequestMapping("/conversations")
public class ConversationController {
    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ConversationDTO>> getConversations(@PathVariable("userId") long userId) {
        List<ConversationDTO> conversations = conversationService.getLastConversations(userId);
        return ResponseEntity.ok(conversations);
    }
}
