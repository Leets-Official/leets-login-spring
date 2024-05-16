package leets.attendance.domain;

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
}