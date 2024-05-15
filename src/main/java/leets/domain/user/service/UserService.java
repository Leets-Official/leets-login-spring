package leets.domain.user.service;

import leets.domain.user.controller.dto.LoginRequest;
import leets.domain.user.controller.dto.TokenResponse;
import leets.domain.user.domain.User;
import leets.domain.user.domain.repository.UserRepository;
import leets.global.EncryptHelper;
import leets.global.auth.JwtProvider;
import leets.global.exception.LeetsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EncryptHelper encryptHelper;
    private final JwtProvider jwtProvider;

    public User save(User user) {
        String encrypt = encryptHelper.encrypt(user.getPassword());
        user.encodePassword(encrypt);
        return userRepository.save(user);
    }

    public boolean checkIdDuplication(String joinId) {
        return userRepository.existsByJoinId(joinId);
    }

    public TokenResponse login(LoginRequest request) {
        User user = userRepository.findByJoinId(request.joinId())
                .orElseThrow(() -> new LeetsException(HttpStatus.FORBIDDEN, "사용자를 찾을 수 없습니다."));
        boolean isMatched = encryptHelper.isMatch(request.password(), user.getPassword());

        if (!isMatched) {
            throw new LeetsException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다");
        }

        String token = jwtProvider.createToken(user);
        return new TokenResponse(token);
    }
}
