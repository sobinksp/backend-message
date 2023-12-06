package dev.tveir.backendmessage.controller;

import dev.tveir.backendmessage.message.ChatRoom;
import dev.tveir.backendmessage.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService service;

    @PostMapping
    public ResponseEntity<ChatRoom> createChatRoom(
            @RequestBody ChatRoom request
    ) {
        return ResponseEntity.ok(service.createChatRoom(request));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ChatRoom>  getMemberChatRoomId(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(service.getUserChatRoomId(id));
    }
}

