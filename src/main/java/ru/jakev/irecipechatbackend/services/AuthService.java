package ru.jakev.irecipechatbackend.services;

import ru.jakev.irecipechatbackend.http.requests.LoginRequest;
import ru.jakev.irecipechatbackend.http.requests.RegisterRequest;

/**
 * @author evotintsev
 * @since 23.02.2024
 */
public interface AuthService {
    //todo: добавить отдельный объект, а не RegisterRequest
    String register(RegisterRequest registerRequest);
    String login(LoginRequest loginRequest);
}
