package com.smartcode.foundation.configuration.security;

import com.smartcode.foundation.domain.dto.UserDetailView;
import com.smartcode.foundation.domain.model.User;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    private final String jwtSecret = "zdtlD3JK56m6wTTgsNFhqzjqP";
    private final String jwtIssuer = "example.io";

    private final Logger logger;

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(format("%s,%s", user.getId(), user.getUsername()))
                .setIssuer(jwtIssuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1 week
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateAccessToken(String useId, String usename, Set<String> roles) {
        return Jwts.builder()
                .setSubject(format("%s,%s", useId, usename))
                .setIssuer(jwtIssuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1 week
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .claim("userId",useId)
                .claim("username",usename)
                .claim("roles",roles)
                .compact();
    }

    public String getUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();


        return claims.getSubject().split(",")[0];
    }

    public UserDetailView getFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        List<String> roles = claims.get("roles", ArrayList.class);
        return  new UserDetailView(
                claims.get("userId", String.class),
                this.getUsername(token),new HashSet<>(roles));
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[1];
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }

}
