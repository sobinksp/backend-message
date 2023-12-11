package dev.tveir.backendmessage.controller;

import dev.tveir.backendmessage.message.ChatRoom;
import dev.tveir.backendmessage.message.Message;
import dev.tveir.backendmessage.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
//@MessageMapping("/api")
public class MessageController {

    private final MessageService service;
    private final SimpMessagingTemplate messagingTemplate;
    /*@MessageMapping("/message") // /app/message
    @SendTo("/chatroom/public")
    public Message sendMessage(@Payload Message message) {
        service.addMessage(message);
        return message;
    }*/

    @MessageMapping("/private-message")
    public Message sendMessage(@Payload Message message) {
        service.addMessage(message);
        messagingTemplate.convertAndSendToUser(message.getRecipient(), "private", message); // /user/USERNAME/private
        return message;
    }

    @GetMapping
    public ResponseEntity<List<Message>> getMessage() {
        return ResponseEntity.ok(service.getAllMessages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Message>>  findByChatId(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(service.getAllMessagesForChat(id));
    }


    /*public ResponseEntity<Void> sendMessage(@Payload Message request) {
        service.addMessage(request);
        messagingTemplate.convertAndSend("/topic/" + request.getChatId());
        return ResponseEntity.ok().build();
    }*/
}
