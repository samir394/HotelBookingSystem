package com.hmsapp.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTservice {

    @Value("${jwt.algorithm.key}")
    private String algorithmkey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry.duration}")
    private int expiry;

    private Algorithm algorithm;

    @PostConstruct
    public void postContruct() {
        System.out.println("Algorithm Key: " + algorithmkey);
        System.out.println("Issuer: " + issuer);
        System.out.println("Expiry Duration: " + expiry);
        algorithm = Algorithm.HMAC256(algorithmkey);
    }

    public String generateToken(String username) {
        return JWT.create()
                .withClaim("name", username)
                .withExpiresAt(new Date(System.currentTimeMillis() + expiry))
                .withIssuer(issuer)
                .sign(algorithm);
    }
    public  String getUsername(String token){
       DecodedJWT decodedJWT = JWT.require(algorithm).
                withIssuer(issuer)
                .build()
                .verify(token);

          return   decodedJWT.getClaim("name").asString();

    }
}
