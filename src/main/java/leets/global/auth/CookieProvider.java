package leets.global.auth;

import jakarta.servlet.http.Cookie;

public class CookieProvider {

    private static final String TOKEN_KEY = "token";

    public Cookie createCookie(final String token) {
        final Cookie cookie = new Cookie(TOKEN_KEY, token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }
}
