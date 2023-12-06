package dev.tveir.backendmessage.controller;

import dev.tveir.backendmessage.message.Message;
import dev.tveir.backendmessage.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService service;

    @PostMapping
    public ResponseEntity<Message> addMessage(
            @RequestBody Message request
    ) {
        return ResponseEntity.ok(service.addMessage(request));
    }

    @GetMapping
    public ResponseEntity<List<Message>> getMessage() {
        return ResponseEntity.ok(service.getAllMessages());
    }


}
