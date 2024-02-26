package ru.jakev.irecipechatbackend.services.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.jakev.irecipechatbackend.entities.User;
import ru.jakev.irecipechatbackend.http.requests.LoginRequest;
import ru.jakev.irecipechatbackend.http.requests.RegisterRequest;
import ru.jakev.irecipechatbackend.services.AuthService;
import ru.jakev.irecipechatbackend.services.JwtService;
import ru.jakev.irecipechatbackend.services.UserService;

/**
 * @author evotintsev
 * @since 23.02.2024
 */
@Service
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserService userService, JwtService jwtService, PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public String register(RegisterRequest registerRequest) {
        User user = new User();
        user.setName(registerRequest.getName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setPhoneNumber(registerRequest.getPhoneNumber());

        if (userService.saveUser(user)) {
            return jwtService.generateToken(user);
        } else {
            return null;
        }
    }

    @Override
    public String login(LoginRequest loginRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return null;
        }

        return jwtService.generateToken((UserDetails) authentication.getPrincipal());
    }
}
