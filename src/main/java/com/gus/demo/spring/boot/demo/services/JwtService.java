package com.gus.demo.spring.boot.demo.services;

import com.gus.demo.spring.boot.demo.models.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    private static final String SECRET = "clave_secreta_muy_larga_para_jwt_123456";
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generarToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key)
                .compact();
    }
    public String extraerUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean validarToken(String token, Usuario user) {
        final String username = extraerUsername(token);
        return username.equals(user.getUsername()) && !estaExpirado(token);
    }
    private boolean estaExpirado(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
