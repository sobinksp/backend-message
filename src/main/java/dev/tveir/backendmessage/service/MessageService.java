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

    public Message saveMessage(Message message) {
        repository.save(message);
        return message;
    }

    public List<Message> getAllMessages() {
        return repository.findAll();
    }

    public List<Message> getAllMessagesForChat(String chatId) {
        return repository.findByChatId(chatId);
    }

}
