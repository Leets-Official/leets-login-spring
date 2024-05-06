package leets.attendance.controller;

import leets.attendance.domain.user.User;
import leets.attendance.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserLoginService userLoginService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User save = userLoginService.save(user);

        URI uri = URI.create("/users/" + save.getId());
        return ResponseEntity.created(uri).build();
    }
}
