package dev.rayh.jwt.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUsername(String token);
    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String jwt, UserDetails userDetails);
}