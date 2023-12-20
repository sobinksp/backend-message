package dev.tveir.backendmessage.service;

import dev.tveir.backendmessage.message.ChatRoom;
import dev.tveir.backendmessage.message.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository repository;

    public ChatRoom createChatRoom(ChatRoom request) {
        if (repository.findByMembers(request.getMembers()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Chat room already exists");
        }
        return repository.save(request);
    }

    public List<ChatRoom> getUserChatRoomId(Integer userId) {
        return repository.findByMembersContaining(userId);
    }
}
