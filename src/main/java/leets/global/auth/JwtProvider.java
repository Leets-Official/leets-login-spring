package leets.global.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import leets.domain.user.domain.User;
import leets.global.config.AuthUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class JwtProvider {

    private static final String NAME = "name";

    private final String secretKey;
    private final long validityInMilliseconds;

    public JwtProvider(@Value("${security.jwt.token.secret-key}") String secretKey,
                       @Value("${security.jwt.token.expire-length}") long expirationMilliseconds) {
        this.secretKey = secretKey;
        this.validityInMilliseconds = expirationMilliseconds;
    }

    public String createToken(final User user) {
        Instant expiredAt = Instant.now().plusMillis(validityInMilliseconds);

        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .claim(NAME, user.getName())
                .setExpiration(Date.from(expiredAt))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    public AuthUser parse(String token) {
        Claims body = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();

        long id = Long.parseLong(body.getSubject());
        String name = body.get(NAME, String.class);
        return new AuthUser(id, name);
    }
}
