package dev.tveir.backendmessage.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private Integer id;
    private String username;
    private String role;
    private String token;
    private String userImageUrl;
}
