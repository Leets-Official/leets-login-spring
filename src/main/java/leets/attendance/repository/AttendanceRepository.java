package leets.attendance.repository;

import leets.attendance.entity.Attendances;
import leets.attendance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendances, Long> {
    List<Attendances> findByUserAndDate(User user, LocalDate date);
    List<Attendances> findByUser(User user);
    List<Attendances> findByUserAndDateBefore(User user, LocalDate date);
    List<Attendances> findAllByUser(User user);
}