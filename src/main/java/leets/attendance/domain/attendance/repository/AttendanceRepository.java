package leets.attendance.domain.attendance.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import leets.attendance.domain.attendance.entity.Attendance;
import leets.attendance.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

  Optional<Attendance> findByMemberAndDate(Member member, LocalDate date);

  List<Attendance> findAllByMember(Member member);
}
