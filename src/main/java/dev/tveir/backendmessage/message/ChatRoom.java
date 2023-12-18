package dev.tveir.backendmessage.message;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "chatroom")
public class ChatRoom {

    @Id
    private String id;
    private List<Integer> members;
}
