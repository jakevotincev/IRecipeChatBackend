package ru.jakev.irecipechatbackend.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.jakev.irecipechatbackend.entities.Message;
import ru.jakev.irecipechatbackend.websocket.messages.MessageDTO;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author evotintsev
 * @since 28.02.2024
 */
@Mapper(componentModel = "spring")
public interface MessageMapper {
    @Mapping(source = "message.sender.id", target = "senderId")
    @Mapping(source = "message.sendDate", target = "sendTimestamp")
    MessageDTO toMessageDTO(Message message);
    @InheritInverseConfiguration
    Message toMessage(MessageDTO messageDTO);

    default long map(LocalDateTime sendDate) {
        return sendDate != null ? sendDate.toInstant(ZoneOffset.UTC).toEpochMilli() : 0;
    }

    default LocalDateTime map(long sendTimestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(sendTimestamp), ZoneOffset.UTC);
    }
}
