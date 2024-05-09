package leets.attendance.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private UUID userId;
    private String name;
    private String part;
}
