package ru.jakev.irecipechatbackend.http.responses;

/**
 * @author evotintsev
 * @since 24.03.2024
 */
//todo: same as for conversations
public record UserDTO(long id, String email, String name, String phoneNumber) {

}
