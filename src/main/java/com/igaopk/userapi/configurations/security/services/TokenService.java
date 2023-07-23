package com.igaopk.userapi.configurations.security.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.igaopk.userapi.users.entitys.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.TemporalAccessor;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("User")
                    .withSubject(user.getUsername())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error generate token");
        }
    }

    public String getSubject(String jwtToken) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("User")
                    .build()
                    .verify(jwtToken)
                    .getSubject();
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Invalid token");
        }
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusMinutes(20).atZone(ZoneId.of("America/Sao_Paulo")).toInstant();
    }
}
