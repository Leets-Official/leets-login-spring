package leets.attendance.domain.user.presentation;

import leets.attendance.domain.user.application.UserService;
import leets.attendance.domain.user.dto.UserRequest;
import leets.attendance.domain.user.dto.UserResponse;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableJpaAuditing
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public void login() {
    }

    @PostMapping(value = "/register")
    public UserResponse register(@RequestBody UserRequest userRequest) throws Exception{
        return userService.register(userRequest);
    }

    @GetMapping(value = "/check-duplicate-id")
    public void checkDuplicateId() {
    }
}
