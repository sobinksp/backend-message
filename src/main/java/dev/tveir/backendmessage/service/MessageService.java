package dev.tveir.backendmessage.service;

import dev.tveir.backendmessage.message.Message;
import dev.tveir.backendmessage.message.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository repository;

    public Message addMessage(Message request) {
        request.setId(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(request);
    }

    public List<Message> getAllMessages() {
        return repository.findAll();
    }
}
