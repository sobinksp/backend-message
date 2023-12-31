package dev.tveir.backendmessage.controller;

import dev.tveir.backendmessage.message.Message;
import dev.tveir.backendmessage.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message")
public class MessageController {

    private final MessageService service;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/private-message")
    public void sendMessage(@Payload Message message) {
        service.saveMessage(message);
        messagingTemplate.convertAndSendToUser(message.getRecipientId(), "private", message); // /user/USERNAME/private
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

    @MessageMapping("/chat")
    public void processMessage(
            @Payload Message message
    ) {
        Message saveMsg = service.saveMessage(message);
        messagingTemplate.convertAndSendToUser(
                message.getRecipientId(),
                "/queue/messages",
                Message.builder()
                        .id(saveMsg.getId())
                        .chatId(saveMsg.getChatId())
                        .senderId(saveMsg.getSenderId())
                        .recipientId(saveMsg.getRecipientId())
                        .content(saveMsg.getContent())
                        .build()
        );
    }
}
