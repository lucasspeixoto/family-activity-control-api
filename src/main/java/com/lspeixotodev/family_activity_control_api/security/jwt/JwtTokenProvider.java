package com.lspeixotodev.family_activity_control_api.security.jwt;

import com.lspeixotodev.family_activity_control_api.dto.authentication.JWTAuthResponse;
import com.lspeixotodev.family_activity_control_api.infra.exceptions.InvalidJwtAuthenticationException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:secret}")
    private String jwtSecret;

    @Value("${security.jwt.token.expire-length:3600000}")
    private long jwtExpirationDate = 3600000;

    public JWTAuthResponse generateToken(String username) {

        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        String accessToken = getAccessToken(username, currentDate, expireDate);

        String refreshToken = getRefreshToken(username, currentDate);

        return new JWTAuthResponse(username, true, currentDate, expireDate, accessToken, refreshToken);
    }

    private String getAccessToken(String username, Date currentDate, Date expireDate) {

        return Jwts.builder()
                .claims()
                .subject(username)
                .issuedAt(currentDate)
                .expiration(expireDate)
                .and()
                .signWith(getSecretKey())
                .compact();
    }

    private String getRefreshToken(String username, Date currentDate) {

        Date expireDate = new Date(currentDate.getTime() + (this.jwtExpirationDate * 3));

        return Jwts.builder()
                .claims()
                .subject(username)
                .issuedAt(currentDate)
                .expiration(expireDate)
                .and()
                .signWith(getSecretKey())
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public JWTAuthResponse generateRefreshedToken(String refreshToken) {
        if (refreshToken.contains("Bearer ")) refreshToken =
                refreshToken.substring("Bearer ".length());

        String username = getUserNameFromJwtToken(refreshToken);

        return generateToken(username);
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parse(token);
            return true;
        } catch (Exception ex) {
            throw new InvalidJwtAuthenticationException("Expired or invalid token!");
        }
    }
}