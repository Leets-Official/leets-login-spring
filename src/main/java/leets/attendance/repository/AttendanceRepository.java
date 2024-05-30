package leets.attendance.repository;

import leets.attendance.domain.Attendance;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface AttendanceRepository extends CrudRepository<Attendance, Long> {
    Optional<Attendance> findById(Long id); // 네임드 쿼리
    @Override
    ArrayList<Attendance> findAll();


    Attendance findAttendanceById(Long id);

}
