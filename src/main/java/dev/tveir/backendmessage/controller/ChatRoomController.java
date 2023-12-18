package dev.tveir.backendmessage.controller;

import dev.tveir.backendmessage.message.ChatRoom;
import dev.tveir.backendmessage.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService service;
    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping
    public ResponseEntity<ChatRoom> createChatRoom(
            @RequestBody ChatRoom request
    ) {
        return ResponseEntity.ok(service.createChatRoom(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ChatRoom>>  getMemberChatRoomId(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(service.getUserChatRoomId(id));
    }

    @MessageMapping("/create-chat-room")
    public void createAndNotifyChatRoom(
            @Payload ChatRoom chatroom
    ) {
        ChatRoom createdChatRoom = service.createChatRoom(chatroom);
        messagingTemplate.convertAndSendToUser(
                String.valueOf(chatroom.getMembers().get(1)),
                "/queue/chatroom",
                ChatRoom.builder()
                        .id(createdChatRoom.getId())
                        .members(createdChatRoom.getMembers())
                        .build()
                );
    }
}

