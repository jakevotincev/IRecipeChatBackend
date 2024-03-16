package ru.jakev.irecipechatbackend.http.responses;

import ru.jakev.irecipechatbackend.websocket.messages.MessageDTO;

import java.util.Set;

/**
 * @author evotintsev
 * @since 08.03.2024
 */
public record ConversationDTO(Long id, Long firstParticipantId, Long secondParticipantId, Set<MessageDTO> messages) {
}
