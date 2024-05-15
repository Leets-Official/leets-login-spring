package leets.domain.attendance.controller.dto;

public record LoginRequest(
        String joinId,
        String password
) {
}
