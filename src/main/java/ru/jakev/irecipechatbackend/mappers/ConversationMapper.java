package ru.jakev.irecipechatbackend.mappers;

import ru.jakev.irecipechatbackend.entities.Conversation;
import ru.jakev.irecipechatbackend.http.responses.ConversationDTO;

/**
 * @author evotintsev
 * @since 08.03.2024
 */
public interface ConversationMapper {
    ConversationDTO toConversationDTO(Conversation conversation);
    Conversation toConversationDTO(ConversationDTO conversationDTO);
}
