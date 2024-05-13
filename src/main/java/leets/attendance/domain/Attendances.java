package leets.attendance.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attendances {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long id;

    @ManyToOne
    private User user;
    private LocalDate date;
    private Boolean attendance;

//    @Enumerated
//    private WeekEnum weekEnum;

    @Builder
    public Attendances(User user, LocalDate date, Boolean attendance) {
        this.user = user;
        this.date = date;
        this.attendance = attendance;
//        this.weekEnum = weekEnum;
    }
}
