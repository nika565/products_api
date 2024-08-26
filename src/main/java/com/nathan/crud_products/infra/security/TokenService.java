package com.nathan.crud_products.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.nathan.crud_products.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    // Gerar token
    public String generateToken(User user) {
        try{

            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Criando o JWT
            String token = JWT.create()
                    .withIssuer("crud-products")
                    .withSubject(user.getEmail())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);
            return token;

        } catch(JWTCreationException exception) {
            throw new RuntimeException("Erro enquanto estava autenticando...");
        }
    }

    // Gerar tempo de expiração do token
    private Instant generateExpirationDate() {
        return LocalDateTime
                .now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-3"));
    }

    // Validação do token
    public String validateToken(String token){
        try{

            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("crud-products")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch(JWTVerificationException exception) {
            return null;
        }
    }

}
