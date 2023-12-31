package dev.tveir.backendmessage.service;

import dev.tveir.backendmessage.model.request.DeleteRequest;
import dev.tveir.backendmessage.model.request.EditRequest;
import dev.tveir.backendmessage.model.response.EditResponse;
import dev.tveir.backendmessage.model.response.UserResponse;
import dev.tveir.backendmessage.user.Role;
import dev.tveir.backendmessage.user.Status;
import dev.tveir.backendmessage.user.User;
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
        repository.save(user);
        return EditResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(String.valueOf(user.getRole()))
                .build();
    }

    public void deleteUser(Integer id) {
        var user = repository.findById(id).orElseThrow();
        repository.delete(user);
    }

    public UserResponse getUserInfo(Integer id) {
        var user = repository.findById(id).orElseThrow();
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(String.valueOf(user.getRole()))
                .userImageUrl(user.getUserImageUrl())
                .build();
    }

    public void connect(User user) {
        var existingUser = repository.findById(user.getId()).orElse(null);
        if (existingUser != null) {
            existingUser.setStatus(Status.ONLINE);
            repository.save(existingUser);
        }
    }
    public void disconnect(User user) {
        var storedUser = repository.findById(user.getId()).orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            repository.save(storedUser);
        }
    }
    public List<User> findConnectedUsers() {
        return repository.findAllByStatus(Status.ONLINE);
    }

}
