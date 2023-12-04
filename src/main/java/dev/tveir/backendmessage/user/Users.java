package dev.tveir.backendmessage.user;

import jakarta.persistence.*;
import lombok.*;

@Data // Generates getters for all fields and setters for all non-final fields. reduce boilerplate
@Builder // Build an object using designing patterns.
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user") // User is a reserved keyword for Postgresql
public class Users {
    @Getter
    @Id // Primary key
    @GeneratedValue() // Auto-increment (default value is Auto)
    private Integer id;
    @Getter
    private String username;
//    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
