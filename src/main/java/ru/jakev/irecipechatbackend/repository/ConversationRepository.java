package ru.jakev.irecipechatbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.jakev.irecipechatbackend.entities.Conversation;

import java.util.List;

/**
 * @author evotintsev
 * @since 28.02.2024
 */
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
   //todo: add distinct and optional
   @Query("from Conversation c join c.users u1 join c.users u2 where u1.id = :firstParticipantId and u2.id = :secondParticipantId AND u1 <> u2")
   Conversation findConversationByParticipantsId(@Param("firstParticipantId") Long firstParticipantId, @Param("secondParticipantId") Long secondParticipantId);
   @Query("from Conversation c join c.users u where u.id = :userId")
   List<Conversation> findConversationByUserId(Long userId);
}
