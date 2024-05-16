package leets.attendance.controller;

import jakarta.validation.Valid;
import leets.attendance.dto.UserRequestDTO;
import leets.attendance.dto.UserResponseDTO;
import leets.attendance.dto.TokenDTO;
import leets.attendance.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/users/register")
    public ResponseEntity<UserResponseDTO> signup(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(authService.signup(userRequestDTO));
    }

    @PostMapping("/users/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(authService.login(userRequestDTO));
    }

    @GetMapping("/users/check-duplicate-id")
    public ResponseEntity<Boolean> checkDuplicateId(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(authService.checkDuplicateId(userRequestDTO));
    }
}