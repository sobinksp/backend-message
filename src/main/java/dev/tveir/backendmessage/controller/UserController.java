package dev.tveir.backendmessage.controller;

import dev.tveir.backendmessage.model.response.AuthenticationResponse;
import dev.tveir.backendmessage.model.response.UserResponse;
import dev.tveir.backendmessage.service.UserService;
import dev.tveir.backendmessage.user.User;
import dev.tveir.backendmessage.user.UserInterface;
import dev.tveir.backendmessage.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/entities")
    public List<Users> getAllEntities() {
        return userService.getAllEntities();
    }
}
