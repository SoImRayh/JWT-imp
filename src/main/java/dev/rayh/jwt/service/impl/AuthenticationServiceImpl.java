package dev.rayh.jwt.service.impl;

import dev.rayh.jwt.model.User;
import dev.rayh.jwt.model.enums.Role;
import dev.rayh.jwt.model.request.AuthenticationRequest;
import dev.rayh.jwt.model.request.RegisterRequest;
import dev.rayh.jwt.model.response.AuthenticationResponse;
import dev.rayh.jwt.repository.UserRepository;
import dev.rayh.jwt.service.AuthenticationService;
import dev.rayh.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .firstName(registerRequest.getFirstname())
                .lastName(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
        }catch (Exception e){
            System.out.println(e.getMessage() );
            throw new BadCredentialsException(e.getMessage());
        }
        var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow(
                () -> new UsernameNotFoundException("user not found")
        );

        var jwtToken = jwtService.generateToken(user);
        return new ResponseEntity<>(new AuthenticationResponse(jwtToken), HttpStatus.OK);
    }

}
