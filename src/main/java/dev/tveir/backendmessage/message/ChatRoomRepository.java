package dev.tveir.backendmessage.message;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    List<ChatRoom> findByMembersContaining(Integer userId);

    boolean existsByMembers(List<Integer> members);
}
