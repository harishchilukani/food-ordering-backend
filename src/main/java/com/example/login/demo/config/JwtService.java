package com.example.login.demo.config;

import com.example.login.demo.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;

import java.util.*;
import java.util.function.Function;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtService {

    private static final String SECRET_KEY = "6C69736D794C6F6E6742656175746966756C5365637265744B6579313233343536";




    // ✅ Generate token with custom claims
    public String generateToken(User user) {
        return Jwts.builder()
                  // Add this line to include extra claims (like userId)

                .claim("roles", user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .claim("userId",user.getId())
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24h
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Extract username from token
    // Extract username (subject)
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract expiration date
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract issued at date
    public Date extractIssuedAt(String token) {
        return extractClaim(token, Claims::getIssuedAt);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public List<String> extractRoles(String token) {
        return extractClaim(token, claims -> {
            Object roles = claims.get("roles");

            if (roles instanceof List<?>) {
                return ((List<?>) roles).stream()
                        .map(Object::toString)
                        .toList();
            } else if (roles instanceof String) {
                return List.of((String) roles);
            }

            return List.of();
        });
    }


//    public List<String> extractAuthorities(String token) {
//        return extractClaim(token, claims -> {
//            Object authorities = claims.get("authorities");
//            if (authorities instanceof List) {
//                return ((List<?>) authorities).stream()
//                        .map(Object::toString)
//                        .collect(Collectors.toList());
//            } else if (authorities instanceof String) {
//                return Arrays.asList((String) authorities);
//            }
//            return new ArrayList<>();
//        });
//    }



    // ✅ Extract all claims using secret key
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey()) // use latest method
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


//    public boolean validateToken(String token) {
//        try{
//            Jwts.parserBuilder()
//                    .setSigningKey(getSignInKey())
//                    .build()
//                    .parseClaimsJws(token);
//        }
//        catch (JwtException ex){
//            return false;
//        }
//        return true;
//    }

    // ✅ Get secret signing key from Base64
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); // Use secure encoded key
        return Keys.hmacShaKeyFor(keyBytes);
    }
    // Check if token is valid
    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username)) && !isTokenExpired(token);
    }

    // Check if token is expired
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


}

