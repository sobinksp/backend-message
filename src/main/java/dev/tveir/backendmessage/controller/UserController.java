package dev.tveir.backendmessage.controller;

import dev.tveir.backendmessage.model.request.AuthenticationRequest;
import dev.tveir.backendmessage.model.request.DeleteRequest;
import dev.tveir.backendmessage.model.request.EditRequest;
import dev.tveir.backendmessage.model.response.EditResponse;
import dev.tveir.backendmessage.model.response.UserResponse;
import dev.tveir.backendmessage.service.UserService;
import dev.tveir.backendmessage.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @PutMapping("/users")
    public ResponseEntity<EditResponse> editUser(
            @RequestBody EditRequest request
            ) {
        return ResponseEntity.ok(service.editUser(request));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserInfo(
            @PathVariable Integer id
            ) {
        return ResponseEntity.ok(service.getUserInfo(id));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Integer id
            ) {
        service.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @MessageMapping("/user.addUser")
    @SendTo("/user/public/connect")
    public User userConnect(@Payload User user) {
        service.connect(user);
        return user;
    }
    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/public/connect")
    public User userDisconnect(@Payload User user) {
        service.disconnect(user);
        return user;
    }

    @GetMapping("/usersOnline")
    public ResponseEntity<List<User>> findConnectedUsers() {
        return ResponseEntity.ok(service.findConnectedUsers());
    }


}
