package ssuPlector.security.provider;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import ssuPlector.global.exception.GlobalException;
import ssuPlector.global.response.code.GlobalErrorCode;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.access-token-validity}")
    private long accessTokenValidityMilliseconds;

    @Value("${jwt.refresh-token-validity}")
    private long refreshTokenValidityMilliseconds;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createAccessToken(Long userId) {
        return createToken(userId, accessTokenValidityMilliseconds);
    }

    public String createRefreshToken(Long userId) {
        return createToken(userId, refreshTokenValidityMilliseconds);
    }

    public String createToken(Long userId, long validityMilliseconds) {
        Claims claims = Jwts.claims();
        claims.put("id", userId);

        Date now = new Date();
        Date expiredDate = new Date(now.getTime() + validityMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiredDate)
                .signWith(this.getSigningKey())
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
            Jws<Claims> claims = getClaims(token);
            Date now = new Date();
            Date expiredDate = claims.getBody().getExpiration();
            return expiredDate.after(now);
        } catch (ExpiredJwtException e) {
            throw new GlobalException(GlobalErrorCode.AUTH_EXPIRED_TOKEN);
        } catch (SecurityException | MalformedJwtException | IllegalArgumentException e) {
            throw new GlobalException(GlobalErrorCode.AUTH_INVALID_TOKEN);
        } catch (UnsupportedJwtException e) {
            throw new GlobalException(GlobalErrorCode.UNSUPPORTED_TOKEN);
        }
    }

    public Jws<Claims> getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
    }

    public Long getId(String token) {
        return getClaims(token).getBody().get("id", Long.class);
    }
}
