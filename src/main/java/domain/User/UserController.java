package domain.User;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import leets.domain.user.controller.dto.IdDuplicationResponse;
import leets.domain.user.controller.dto.LoginRequest;
import leets.domain.user.controller.dto.TokenResponse;
import leets.domain.user.service.UserService;
import leets.global.auth.CookieProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final CookieProvider cookieProvider = new CookieProvider();

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User save = userService.save(user);

        URI uri = URI.create("/users/" + save.getId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/duplication/{Id}")
    public ResponseEntity<IdDuplicationResponse> checkIdDuplication(@PathVariable String Id) {
        boolean isDuplicated = userService.checkIdDuplication(Id);

        IdDuplicationResponse body = new IdDuplicationResponse(isDuplicated);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        TokenResponse tokenResponse = userService.login(request);

        Cookie cookie = cookieProvider.createCookie(tokenResponse.accessToken());
        response.addCookie(cookie);

        return ResponseEntity.ok(tokenResponse);
    }
}

