package com.hcld2.jwtdemo.service;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private final Algorithm algorithm;
    private final JWTVerifier verifier;
    private final long expireMs;

    public JwtService(@Value ("${app.jwt.secret}") String secret, @Value("${app.jwt.expiration-ms}") long expireMs){
        this.algorithm= Algorithm.HMAC256(secret);
        this.verifier= JWTVerifier(algorithm).build();
        this.expireMs= expireMs;
    }
    public String generateToken(String username, String pasword){
        Date now= new Date();
        return JWT.create()
                .withsubject(username)
                .withIssueAt(now)
                .withExpiresAt(new Date(now.getTime() + expireMs))
                .sign(algorithm);

    }
    public String verifyAndGetUsername(String token){
        
    }
}
