package leets.attendance.domain.attendance.Repository;

import leets.attendance.domain.attendance.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
    Optional<Attendance> findByAttendanceId(Long attendanceId);

}
