package com.idris.membership.service;

import com.idris.membership.model.User;
import com.idris.membership.repository.UserRepository;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class JwtService {

    @Value("${jwt.secret-key}")
    private String jwtSecretKey;

    @Value("${jwt.expired-in-hour}")
    private int jwtExpiredInHour;

    @Autowired
    UserRepository userRepository;

    public String generateToken(User user) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSecretKey.getBytes());
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("email", user.getEmail())
                .expiration(new Date( new Date().getTime() + (jwtExpiredInHour * 60 * 60 * 1000)))
                .issuedAt(new Date())
                .signWith(key)
                .compact();
    }

    public User verifyToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(jwtSecretKey.getBytes());
            String email = Jwts.parser().verifyWith(key).build().parseSignedClaims( token.replaceFirst("Bearer", "").trim() ).getPayload().get("email", String.class);
            return userRepository.getUserByEmail(email).get();
        } catch (Exception ex) {
            return null;
        }
    }
}
