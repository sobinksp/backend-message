package dev.tveir.backendmessage.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "message")
public class Message {

    @Id
    private String id;
    private String chatId;
    private String sender;
    private String recipient;
    private String content;

    @CreatedDate
    private Instant timestamp;
}
