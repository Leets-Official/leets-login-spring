package domain.User;

import domain.User.User;
import domain.User.UserRepo;
import domain.User.LoginRequest;
import domain.User.TokenResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final EncryptHelper encryptHelper;
    private final JwtProvider jwtProvider;

    public leets.attendance.User save(leets.attendance.User user) {
        String encrypt = encryptHelper.encrypt(user.getPassword());
        user.encodePassword(encrypt);
        return userRepo.save(user);
    }

    public boolean checkIdDuplication(String joinId) {
        return userRepo.existsByJoinId(joinId);
    }

    public TokenResponse login(LoginRequest request) {
        User user = userRepo.findById(request.Id())
                .orElseThrow(() -> new LeetsException(HttpStatus.FORBIDDEN, "사용자를 찾을 수 없습니다."));
        boolean isMatched = encryptHelper.isMatch(request.password(), user.getPassword());

        if (!isMatched) {
            throw new LeetsException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다");
        }

        String token = jwtProvider.createToken(user);
        return new TokenResponse(token);
    }
}


