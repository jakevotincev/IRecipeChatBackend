package ru.jakev.irecipechatbackend.mappers;

import org.springframework.stereotype.Component;
import ru.jakev.irecipechatbackend.entities.Conversation;
import ru.jakev.irecipechatbackend.entities.User;
import ru.jakev.irecipechatbackend.http.responses.ConversationDTO;
import ru.jakev.irecipechatbackend.websocket.messages.MessageDTO;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author evotintsev
 * @since 08.03.2024
 */
@Component
public class ConversationMapperImpl implements ConversationMapper {
    private final MessageMapper messageMapper;

    public ConversationMapperImpl(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @Override
    public ConversationDTO toConversationDTO(Conversation conversation) {
        if (conversation.getUsers().size() > 2) {
            throw new IllegalArgumentException("Conversation has more than 2 participants, for now supports only 2 participants.");
        } else if (conversation.getUsers().size() < 2) {
            throw new IllegalArgumentException("Conversation has less than 2 participants.");
        }

        long id = conversation.getId();
        Iterator<User> userIterator = conversation.getUsers().iterator();
        long firstParticipantId = userIterator.next().getId();
        long secondParticipantId = userIterator.next().getId();
        Set<MessageDTO> messages = conversation.getMessages().stream().map(messageMapper::toMessageDTO)
                .collect(Collectors.toSet());
        //todo: refactor
        messages.forEach(messageDTO -> messageDTO.setRecipientId(messageDTO.getSenderId() == firstParticipantId ? secondParticipantId : firstParticipantId));

        return new ConversationDTO(id, firstParticipantId, secondParticipantId, messages);
    }

    @Override
    public Conversation toConversationDTO(ConversationDTO conversationDTO) {
        return null;
    }
}
