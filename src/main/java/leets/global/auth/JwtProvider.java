package leets.global.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import leets.domain.attendance.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class JwtProvider {

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
                .setExpiration(Date.from(expiredAt))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }
}
