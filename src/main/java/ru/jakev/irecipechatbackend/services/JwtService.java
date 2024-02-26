package ru.jakev.irecipechatbackend.services;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author evotintsev
 * @since 26.02.2024
 */
public interface JwtService {
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
    String extractEmail(String token);
}
