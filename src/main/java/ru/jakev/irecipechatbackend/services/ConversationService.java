package ru.jakev.irecipechatbackend.services;

import ru.jakev.irecipechatbackend.entities.Conversation;
import ru.jakev.irecipechatbackend.http.responses.ConversationDTO;

import java.util.List;
import java.util.Optional;

/**
 * @author evotintsev
 * @since 28.02.2024
 */
public interface ConversationService {
    //todo: return dtos
    Conversation createConversation(Long firstParticipantId, Long secondParticipantId);
    Optional<Conversation> getConversation(Long firstParticipantId, Long secondParticipantId);
    Optional<Conversation> getConversationById(Long id);
    List<ConversationDTO> getLastConversations(Long userId);
}
