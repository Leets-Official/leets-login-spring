package leets.global.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import leets.global.exception.LeetsException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class CookieProvider {

    private static final String TOKEN_KEY = "token";

    public Cookie createCookie(final String token) {
        final Cookie cookie = new Cookie(TOKEN_KEY, token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }

    public List<Cookie> extractCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            throw new LeetsException(NOT_FOUND, "쿠키가 존재하지 않습니다");
        }
        return List.of(cookies);
    }

    public String extractToken(List<Cookie> cookies) {
        if (cookies == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        return cookies.stream()
                .filter(cookie -> cookie.getName().equals(TOKEN_KEY))
                .findFirst()
                .orElseThrow(() -> new LeetsException(UNAUTHORIZED, "로그인이 필요합니다."))
                .getValue();
    }
}
