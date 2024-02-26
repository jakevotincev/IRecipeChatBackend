package ru.jakev.irecipechatbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.jakev.irecipechatbackend.http.requests.LoginRequest;
import ru.jakev.irecipechatbackend.http.requests.RegisterRequest;
import ru.jakev.irecipechatbackend.http.responses.AuthResponse;
import ru.jakev.irecipechatbackend.services.AuthService;

/**
 * @author evotintsev
 * @since 23.02.2024
 */
@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
    //todo: add swagger
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //todo: controller advise
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        //todo: add check confirm password
        String token = authService.register(registerRequest);


        AuthResponse response = new AuthResponse();
        if (token != null) {
            response.setToken(token);
//            response.setRefreshToken();
        } else {
            response.setError("Error occurred while registration");
        }

        return token != null ? ResponseEntity.ok(response) :
                ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest);
        AuthResponse response = new AuthResponse();
        if (token != null) {
            response.setToken(token);
//            response.setRefreshToken();
        } else {
            response.setError("Error occurred while login");
        }
        return token != null ? ResponseEntity.ok(response) :
                ResponseEntity.badRequest().body(response);
    }
}
