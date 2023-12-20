package dev.tveir.backendmessage.service;

import dev.tveir.backendmessage.message.ChatRoom;
import dev.tveir.backendmessage.message.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository repository;

    public ChatRoom createChatRoom(ChatRoom request) {
        return repository.save(request);
    }

    public List<ChatRoom> getUserChatRoomId(Integer userId) {
        return repository.findByMembersContaining(userId);
    }
}
