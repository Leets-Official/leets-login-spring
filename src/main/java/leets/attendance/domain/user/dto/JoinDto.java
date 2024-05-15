package leets.attendance.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JoinDto {
    private String joinId;
    private String password;
    private String checkPassword;
    private String name;
    private String part;
}
