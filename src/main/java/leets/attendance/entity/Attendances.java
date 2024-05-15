package leets.attendance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
public class Attendances {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long id;

    @ManyToOne
    private User user;
    private LocalDate date;
    private Boolean attendance;

    @Builder
    public Attendances(User user, LocalDate date, Boolean attendance) {
        this.user = user;
        this.date = date;
        this.attendance = attendance;
    }
}