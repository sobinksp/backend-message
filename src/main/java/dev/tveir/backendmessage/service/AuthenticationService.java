package dev.tveir.backendmessage.service;

import dev.tveir.backendmessage.model.request.AuthenticationRequest;
import dev.tveir.backendmessage.model.response.AuthenticationResponse;
import dev.tveir.backendmessage.model.request.RegisterRequest;
import dev.tveir.backendmessage.model.request.VerificationRequest;
import dev.tveir.backendmessage.user.Role;
import dev.tveir.backendmessage.user.User;
import dev.tveir.backendmessage.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        // check if username is already registered
        if (repository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .role(String.valueOf(user.getRole()))
                .username(String.valueOf(user.getUsername()))
                .id(user.getId())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = repository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .role(String.valueOf(user.getRole()))
                .username(String.valueOf(user.getUsername()))
                .id(user.getId())
                .build();
    }

    public AuthenticationResponse verification(VerificationRequest request) {
        var user = repository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .role(String.valueOf(user.getRole()))
                .username(String.valueOf(user.getUsername()))
                .id(user.getId())
                .build();
    }
}
