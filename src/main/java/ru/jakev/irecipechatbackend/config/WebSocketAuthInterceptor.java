package ru.jakev.irecipechatbackend.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.jakev.irecipechatbackend.services.JwtService;
import ru.jakev.irecipechatbackend.services.UserService;

/**
 * @author evotintsev
 * @since 26.02.2024
 */
@Component
public class WebSocketAuthInterceptor implements ChannelInterceptor {
    private final JwtService jwtService;
    private final UserService userService;
    private final Logger LOG = LoggerFactory.getLogger(WebSocketAuthInterceptor.class);

    public WebSocketAuthInterceptor(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    //todo: improve exception handling
    //todo: add logic with token check in one place
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        final var accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        final var cmd = accessor.getCommand();
        String jwt;
        if (StompCommand.CONNECT == cmd || StompCommand.SEND == cmd || StompCommand.SUBSCRIBE == cmd) {
            try {
                final var requestTokenHeader = accessor.getFirstNativeHeader("Authorization");
                if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
                    jwt = requestTokenHeader.substring(7);
                } else {
                    LOG.error("Invalid token format");
                    throw new SecurityException("Invalid token format");
                }

                String email = jwtService.extractEmail(jwt);

                UserDetails userDetails = userService
                        .loadUserByUsername(email);
                if (!jwtService.isTokenValid(jwt, userDetails)) {
                    LOG.error("Token is not valid");
                    throw new SecurityException("Token is not valid");
                }
            } catch (Exception e) {
                LOG.error("Error while authenticate message", e);
                throw new SecurityException("Error while processing message");
            }

        }
        return message;
    }
}
