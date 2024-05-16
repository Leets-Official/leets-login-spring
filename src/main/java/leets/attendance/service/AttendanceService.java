package leets.attendance.service;

import leets.attendance.entity.Attendances;
import leets.attendance.entity.User;
import leets.attendance.repository.AttendanceRepository;
import leets.attendance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;

    //해당 날짜에 출석체크
    public void makeAttendance(String username, LocalDate date) {
        User user = userRepository.findByUsername(username);
        List<Attendances> attendancesList = attendanceRepository.findByUserAndDate(user, date);

        if (attendancesList.isEmpty()) {
            log.error("해당 날짜에 정기 모임이 없습니다.");
            return;
        }

        boolean alreadyAttendedToday = attendancesList.stream()
                .anyMatch(attendance -> attendance.getDate().isEqual(date));

        Optional<Attendances> attendanceToday = attendancesList.stream()
                .filter(attendance -> attendance.getDate().equals(date))
                .findFirst();

        if (attendanceToday.isPresent()) {
            Attendances attendance = attendanceToday.get();
            if (!attendance.getAttendance()) {
                updateAttendance(attendance, true);
                log.info("출석되었습니다.");
            } else {
                log.info("이미 출석되었습니다.");
            }
        }
    }

    private void updateAttendance(Attendances attendance, boolean isAttended) {
        attendance.setAttendance(isAttended);
        attendanceRepository.save(attendance);
    }


    //출석률 반환
    public double getAttendanceRate(String userid, LocalDate date) {
        User user = userRepository.findByUsername(userid);
        List<Attendances> attendancesList = attendanceRepository.findByUserAndDate(user, date);
        List<Attendances> totalAttendancesList = attendanceRepository.findByUser(user);
        double totalCount = totalAttendancesList.size();

        long countOfTrueAttendances = attendancesList.stream()
                .filter(attendance -> attendance.getAttendance().equals(true))
                .count();

        double percent = ((countOfTrueAttendances / totalCount) * 100);
        percent = Math.round(percent * 100.0) / 100.0;
        return percent;
    }

    //출석 데이터 반환
    public List<Attendances> getAllAttendanceRecord(String userid){
        User user = userRepository.findByUsername(userid);
        List<Attendances> allAttendances = attendanceRepository.findAllByUser(user);
        return ResponseEntity.ok(allAttendances).getBody();
    }
}