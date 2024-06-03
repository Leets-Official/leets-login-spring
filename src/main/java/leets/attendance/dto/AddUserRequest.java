package leets.attendance.dto;

import leets.attendance.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class AddUserRequest {
    private String email;
    private String password;
    private String nickname;

    public User toEntity() {
        return new User(email, password, nickname);
    }
    @Override
    public String toString() {
        return "AddUserRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}