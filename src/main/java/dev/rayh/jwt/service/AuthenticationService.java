package dev.rayh.jwt.service;

import dev.rayh.jwt.model.request.AuthenticationRequest;
import dev.rayh.jwt.model.request.RegisterRequest;
import dev.rayh.jwt.model.response.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest registerRequest);

    ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest);
}
