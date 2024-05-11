package leets.attendance.domain.member.service;

import java.util.Optional;
import leets.attendance.domain.member.entity.Member;
import leets.attendance.domain.member.model.request.IdDuplicationCheckRequest;
import leets.attendance.domain.member.model.request.LoginRequest;
import leets.attendance.domain.member.model.request.RegisterRequest;
import leets.attendance.domain.member.model.response.LoginResponse;
import leets.attendance.domain.member.repository.MemberRepository;
import leets.attendance.global.auth.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public void register(RegisterRequest request) {

    if (!verifyPasswordMatch(request.getPassword(), request.getPasswordConfirmation())) {
      throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
    }

    Member member = Member.createMember()
        .name(request.getName())
        .part(request.getPart())
        .username(request.getUsername())
        .password(passwordEncoder.encode(request.getPassword()))
        .build();

    memberRepository.save(member);
  }

  public LoginResponse login(LoginRequest request) {
    Optional<Member> optionalMember = memberRepository.findByUsername(request.getUsername());
    if (optionalMember.isEmpty()) {
      throw new UsernameNotFoundException("존재하지 않는 아이디입니다.");
    }
    Member member = optionalMember.get();

    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
    }

    String token = jwtService.generateToken(member);
    return LoginResponse.builder().token(token).build();
  }

  /**
   * @return 아이디가 중복일 경우 TRUE, 중복이 아닐 경우 FALSE
   *
   * 응답 값을 boolean으로 할지 String으로 해서 명시적으로 보여줄 지 고민중
   */
  public boolean checkDuplicateId(IdDuplicationCheckRequest request) {
    Optional<Member> member = memberRepository.findByUsername(request.getUsername());
    return member.isPresent();
  }

  private boolean verifyPasswordMatch(String password, String passwordConfirmation) {
    return password.equals(passwordConfirmation);
  }
}
