package leets.attendance.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import leets.attendance.config.JWTUtil;
import leets.attendance.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.net.URLEncoder;

@RequiredArgsConstructor
@Slf4j
public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String userid = obtainUsername(request);
        String password = obtainPassword(request);

        log.info("Attempting to authenticate user {}", userid);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userid, password);

        return authenticationManager.authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("Successfully authenticated user {}", authResult.getPrincipal());
        User user = (User) authResult.getPrincipal();

        String userId = user.getUsername();
        String accessToken = jwtUtil.createAccessToken(userId);

        response.addHeader("Authorization", "Bearer " + accessToken);//꼭 이렇게 넣어야되나?, 한 번만 넣어주면 계속 토큰이 확인되나?
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "";

        if (exception instanceof UsernameNotFoundException) {
            errorMessage = "유저없음";
            log.error(errorMessage);
        } else if (exception instanceof BadCredentialsException) {
            errorMessage = "비밀번호틀림";
            log.error(errorMessage);
        } else {
            errorMessage = "로그인 정보를 다시 입력해주세요.";
            log.error(errorMessage);
        }

        errorMessage = URLEncoder.encode(errorMessage, "UTF-8");

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(errorMessage);
    }
}
