package dev.tveir.backendmessage.service;

import dev.tveir.backendmessage.message.Message;
import dev.tveir.backendmessage.message.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository repository;
    private final ChatRoomService chatRoomService;

    public List<Message> getAllMessages() {
        return repository.findAll();
    }

    public List<Message> getAllMessagesForChat(String chatId) {
        return repository.findByChatId(chatId);
    }

    public Message saveMessage(Message message) {
        return repository.save(message);
    }

    public List<Message> findChatMessages(
            String senderId,
            String recipientId
    ) {
        var chatId = chatRoomService.getChatRoomId(
                senderId,
                recipientId,
                false);
        return chatId.map(repository::findByChatId).orElse(new ArrayList<>());
    }
}
