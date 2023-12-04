package dev.tveir.backendmessage.service;

import dev.tveir.backendmessage.model.request.EditRequest;
import dev.tveir.backendmessage.model.response.EditResponse;
import dev.tveir.backendmessage.model.response.UserResponse;
import dev.tveir.backendmessage.user.Role;
import dev.tveir.backendmessage.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<UserResponse> getAllUsers() {
        var userList = repository.findAll();
        return userList.stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .role(String.valueOf(user.getRole()))
                        .build())
                .collect(Collectors.toList()
        );
    }

    public EditResponse editUser(EditRequest request) {
        var user = repository.findByUsername(request.getUsername()).orElseThrow();
        Role newRole = Role.valueOf(request.getRole());
        user.setRole(newRole);

        return EditResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(String.valueOf(user.getRole()))
                .build();
    }
}
