package com.shopping.security.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.shopping.security.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private static final String ISSUER = "auth-security";

    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            //DEFINIÇÃO DO TOKEN                                 //ALGORITMO DE CRIAÇÃO DO TOKEN
            String token = JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getUsername())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);//assinatura e geração final
            return token;
            
        }catch (JWTVerificationException exception){
            //return  exception.getMessage();
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    //identificação de token/usuário
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()   //contruindo token
                    .verify(token) //decodeJWT
                    .getSubject();  //pegando o subject que foi salvo com o toke
        }   catch (JWTVerificationException exception){
            return exception.getMessage();
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}


