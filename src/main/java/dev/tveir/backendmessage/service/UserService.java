package dev.tveir.backendmessage.service;

import dev.tveir.backendmessage.user.User;
import dev.tveir.backendmessage.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }
}
