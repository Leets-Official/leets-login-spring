package leets.domain.attendance.domain;

import jakarta.persistence.*;
import leets.domain.user.domain.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity(name = "attendances")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    @Column(nullable = false)
    private LocalDateTime localDateTime;

    public Attendance() {
    }

    public Attendance(User user, AttendanceStatus status, LocalDateTime localDateTime) {
        this.user = user;
        this.status = status;
        this.localDateTime = localDateTime;
    }
}
