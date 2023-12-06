package dev.tveir.backendmessage.message;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    ChatRoom findByMembersContaining(Integer userId);
}
