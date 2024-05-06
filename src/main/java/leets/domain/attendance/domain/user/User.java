package leets.domain.attendance.domain.user;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Position position;

    public User encodePassword(String encode) {
        this.password = encode;
        return this;
    }
}

