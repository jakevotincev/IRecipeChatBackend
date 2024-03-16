package ru.jakev.irecipechatbackend.services.impl;

import org.springframework.stereotype.Service;
import ru.jakev.irecipechatbackend.entities.Conversation;
import ru.jakev.irecipechatbackend.entities.User;
import ru.jakev.irecipechatbackend.http.responses.ConversationDTO;
import ru.jakev.irecipechatbackend.mappers.ConversationMapper;
import ru.jakev.irecipechatbackend.repository.ConversationRepository;
import ru.jakev.irecipechatbackend.services.ConversationService;
import ru.jakev.irecipechatbackend.services.UserService;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author evotintsev
 * @since 28.02.2024
 */
@Service
public class ConversationServiceImpl implements ConversationService {
    private final ConversationRepository conversationRepository;
    private final UserService userService;
    private final ConversationMapper conversationMapper;

    public ConversationServiceImpl(ConversationRepository conversationRepository, UserService userService,
                                   ConversationMapper conversationMapper) {
        this.conversationRepository = conversationRepository;
        this.userService = userService;
        this.conversationMapper = conversationMapper;
    }

    @Override
    public Conversation createConversation(Long firstParticipantId, Long secondParticipantId) {
        //todo: add check for existing conversation
        User firtsUser = userService.getUserById(firstParticipantId).orElseThrow(()
                -> new IllegalArgumentException("User with id " + firstParticipantId + " not found"));
        User secondUser = userService.getUserById(secondParticipantId).orElseThrow(() ->
                new IllegalArgumentException("User with id " + secondParticipantId + " not found"));
        Set<User> users = new HashSet<>(Arrays.asList(firtsUser, secondUser));

        Conversation conversationToSave = new Conversation.Builder()
                .setCreationDate(LocalDateTime.now())
                .withUsers(users)
                .build();
        return conversationRepository.save(conversationToSave);
    }

    @Override
    public Optional<Conversation> getConversation(Long firstParticipantId, Long secondParticipantId) {
        Conversation conversation = conversationRepository
                .findConversationByParticipantsId(firstParticipantId, secondParticipantId);

        return conversation == null ? Optional.empty() : Optional.of(conversation);
    }

    @Override
    public Optional<Conversation> getConversationById(Long id) {
        Conversation conversation = conversationRepository.findById(id).orElse(null);
        return conversation == null ? Optional.empty() : Optional.of(conversation);
    }

    //todo: make return last conversations (do it by find lsat messages with distinct conversation and than return conversations)
    //todo: limit by number of messages
    //todo: add more methods, get by timestamp etc
    //todo: for now it returns all conversations
    @Override
    public List<ConversationDTO> getLastConversations(Long userId) {
        if (!validateUser(userId)) {
            return List.of();
        }

        List<Conversation> conversations = conversationRepository.findConversationByUserId(userId);
        return convertToDTO(conversations);
    }

    private List<ConversationDTO> convertToDTO(List<Conversation> conversations) {
        if (conversations.isEmpty()) {
            return List.of();
        }

        return conversations.stream().map(conversationMapper::toConversationDTO).toList();
    }

    private boolean validateUser(long userId) {
        //todo: add more validations in future
        return userService.isUserExists(userId);
    }
}
