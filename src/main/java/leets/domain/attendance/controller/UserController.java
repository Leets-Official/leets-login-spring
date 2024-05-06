package leets.domain.attendance.controller;

import leets.domain.attendance.controller.dto.JoinIdDuplicationResponse;
import leets.domain.attendance.domain.user.User;
import leets.domain.attendance.service.UserService;
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

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User save = userService.save(user);

        URI uri = URI.create("/users/" + save.getId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/duplication/{joinId}")
    public ResponseEntity<JoinIdDuplicationResponse> checkIdDuplication(@PathVariable String joinId) {
        boolean isDuplicated = userService.checkIdDuplication(joinId);

        JoinIdDuplicationResponse body = new JoinIdDuplicationResponse(isDuplicated);
        return ResponseEntity.ok(body);
    }
}
