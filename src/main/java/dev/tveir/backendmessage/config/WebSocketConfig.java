package dev.tveir.backendmessage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker (MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app"); // Set the prefix for client-to-server messages
        registry.enableSimpleBroker("/topic", "/chatroom", "/user"); // Enables a simple in-memory message broker to broadcast messages to clients;
        registry.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS(); // WebSocket endpoint for clients to connect, wss for https
    }

}
