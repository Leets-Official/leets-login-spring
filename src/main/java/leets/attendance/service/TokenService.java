package leets.attendance.service;

import leets.attendance.config.jwt.TokenProvider;
import leets.attendance.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String email) {
        // 이메일을 가지고 사용자 정보를 찾습니다.
        User user = userService.findByEmail(email);

        // 사용자 정보가 없으면 예외를 발생시킵니다.
        if (user == null) {
            throw new IllegalArgumentException("User not found with email: " + email);
        }
        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}