package leets.attendance.controller;

import jakarta.validation.Valid;
import leets.attendance.dto.UserDTO;
import leets.attendance.service.UserJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class UserJoinController {

    private final UserJoinService userJoinService;

    @PostMapping("/users/register")
    public ResponseEntity<String> registerUser(@Valid UserDTO dto, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation error: Please check your input.");
        }
        userJoinService.register(dto);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/users/login")).build();
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("IllegalStateException: " + e.getMessage());
    }
}
