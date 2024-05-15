package leets.attendance.domain.member.controller;

import leets.attendance.domain.member.model.request.IdDuplicationCheckRequest;
import leets.attendance.domain.member.model.request.LoginRequest;
import leets.attendance.domain.member.model.request.RegisterRequest;
import leets.attendance.domain.member.model.response.LoginResponse;
import leets.attendance.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @PostMapping("/register")
  public ResponseEntity<Void> register(@RequestBody RegisterRequest request) {
    memberService.register(request);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
    return ResponseEntity.ok(memberService.login(request));
  }

  @GetMapping("/check-duplicate-id")
  public ResponseEntity<Boolean> checkDuplicateId(@RequestBody IdDuplicationCheckRequest request) {
    return ResponseEntity.ok(memberService.checkDuplicateId(request));
  }
}
