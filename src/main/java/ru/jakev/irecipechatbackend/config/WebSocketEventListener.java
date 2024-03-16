package ru.jakev.irecipechatbackend.config;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

/**
 * @author evotintsev
 * @since 08.03.2024
 */
@Component
public class WebSocketEventListener implements ApplicationListener<SessionConnectedEvent> {

    public WebSocketEventListener() {

    }

    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
       //todo: maybe will be useful in future
    }
}
