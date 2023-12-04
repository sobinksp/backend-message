package dev.tveir.backendmessage.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditRequest {
    private Integer id;
    private String username;
    private String role;
}