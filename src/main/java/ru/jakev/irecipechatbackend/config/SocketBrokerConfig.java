package ru.jakev.irecipechatbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.GsonMessageConverter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import ru.jakev.irecipechatbackend.config.security.WebSocketAuthInterceptor;
import ru.jakev.irecipechatbackend.websocket.DefaultWebSocketMessageSender;
import ru.jakev.irecipechatbackend.websocket.WebSocketMessageSender;

/**
 * @author evotintsev
 * @since 26.02.2024
 */
@Configuration
@EnableWebSocketMessageBroker
public class SocketBrokerConfig implements WebSocketMessageBrokerConfigurer {

    private final WebSocketAuthInterceptor authInterceptor;

    public SocketBrokerConfig(WebSocketAuthInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/all", "/specific");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/irecipe-chat").setAllowedOriginPatterns("*");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(authInterceptor);
        WebSocketMessageBrokerConfigurer.super.configureClientInboundChannel(registration);
    }

    @Bean
    public WebSocketMessageSender simpMessagingTemplate(SimpMessagingTemplate simpMessagingTemplate){
        simpMessagingTemplate.setMessageConverter(new GsonMessageConverter());
        return new DefaultWebSocketMessageSender(simpMessagingTemplate);
    }
}
