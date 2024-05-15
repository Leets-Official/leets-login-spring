package leets.domain.user.controller.dto;

public record LoginRequest(
        String joinId,
        String password
) {
}
