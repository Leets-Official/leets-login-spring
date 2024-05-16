package leets.attendance.domain.attendance.domain;


import jakarta.persistence.*;
import leets.attendance.domain.user.domain.User;
import leets.attendance.global.BaseTimeEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Attendance extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long attendanceId;

    @Column(nullable = false)
    private Integer week;

    @Column(nullable = false)
    private Boolean isAttend;

    @Column
    private LocalDate attendanceTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
