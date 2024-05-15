package leets.attendance.domain.member.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {

  private String token;
}
