package ru.jakev.irecipechatbackend.mappers;

import org.mapstruct.Mapper;
import ru.jakev.irecipechatbackend.entities.User;
import ru.jakev.irecipechatbackend.http.responses.UserDTO;

/**
 * @author evotintsev
 * @since 24.03.2024
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);
    User toUser(UserDTO userDTO);

}
