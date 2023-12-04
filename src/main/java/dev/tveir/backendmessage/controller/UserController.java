package dev.tveir.backendmessage.controller;

import dev.tveir.backendmessage.model.request.AuthenticationRequest;
import dev.tveir.backendmessage.model.request.DeleteRequest;
import dev.tveir.backendmessage.model.request.EditRequest;
import dev.tveir.backendmessage.model.response.EditResponse;
import dev.tveir.backendmessage.model.response.UserResponse;
import dev.tveir.backendmessage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @DeleteMapping("/users")
    public ResponseEntity<Void> deleteUser(
            @RequestBody DeleteRequest request
            ) {
        service.deleteUser(request);
        return ResponseEntity.ok().build();
    }
}
