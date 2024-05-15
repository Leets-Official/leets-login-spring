package leets.global.config;

public class AuthUser {

    private final Long id;
    private final String name;

    public AuthUser(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
