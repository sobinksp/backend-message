package dev.tveir.backendmessage.controller;

import dev.tveir.backendmessage.model.request.AuthenticationRequest;
import dev.tveir.backendmessage.model.response.AuthenticationResponse;
import dev.tveir.backendmessage.service.AuthenticationService;
import dev.tveir.backendmessage.model.request.RegisterRequest;
import dev.tveir.backendmessage.model.request.VerificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> authenticate (
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/verification")
    public ResponseEntity<AuthenticationResponse> verification (
            @RequestBody VerificationRequest request
    ) {
        return ResponseEntity.ok(service.verification(request));
    }
}
