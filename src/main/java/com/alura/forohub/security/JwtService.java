package com.alura.forohub.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class JwtService {
  private final SecretKey key;
  private final long expirationMs;

  public JwtService(@Value("${security.jwt.secret}") String secret,
                    @Value("${security.jwt.expiration-minutes:120}") long expirationMinutes) {
    // If provided string is already base64 or plain, Keys.hmacShaKeyFor will handle byte[] size
    this.key = Keys.hmacShaKeyFor(secret.getBytes());
    this.expirationMs = expirationMinutes * 60_000L;
  }

  public String generateToken(String username) {
    Date now = new Date();
    Date exp = new Date(now.getTime() + expirationMs);
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(now)
        .setExpiration(exp)
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  public String extractUsername(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }
}
