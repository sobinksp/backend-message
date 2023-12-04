package dev.tveir.backendmessage.service;

import dev.tveir.backendmessage.model.response.AuthenticationResponse;
import dev.tveir.backendmessage.model.response.UserResponse;
import dev.tveir.backendmessage.user.User;
import dev.tveir.backendmessage.user.UserInterface;
import dev.tveir.backendmessage.user.UserRepository;
import dev.tveir.backendmessage.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserInterface repositoryInterface;
    public List<Users> getAllEntities() {
        return repositoryInterface.findAllProjectedBy();
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }
}
