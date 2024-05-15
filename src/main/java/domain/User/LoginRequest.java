package domain.User;

public record LoginRequest (
    String Id,
    String password
) {
}
