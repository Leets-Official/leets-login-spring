package leets.attendance.repository;

import leets.attendance.domain.Attendance;
import leets.attendance.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    ArrayList<Attendance> findById(String id); // 네임드 쿼리

    @Override
    ArrayList<Attendance> findAll();
}
