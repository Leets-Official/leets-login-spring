package leets.attendance.service;

import leets.attendance.domain.Attendances;
import leets.attendance.domain.User;
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

    public void makeAttendance(String userid, LocalDate date) {
        User user = userRepository.findByUserid(userid);
        List<Attendances> attendancesList = attendanceRepository.findByUserAndDate(user, date);
        //해당 유저와 날짜에 대해서 출석이 있다면 날짜가 오늘과 같은지 확인하고 attendance를 true로 바꿈
        if (!attendancesList.isEmpty()) {
            boolean alreadyAttendedToday = attendancesList.stream()
                    .anyMatch(attendance -> attendance.getDate().isEqual(date));
            if (alreadyAttendedToday) {
                Optional<Attendances> attendanceToday = attendancesList.stream()
                        .filter(attendance -> attendance.getDate().equals(date))
                        .findFirst();

                Attendances attendance = attendanceToday.get();
                if(!attendance.getAttendance()) {
                    attendance.setAttendance(true);
                    attendanceRepository.save(attendance);
                    log.info("출석되었습니다.");
                } else{
                    log.info("이미 출석되었습니다.");
                }
            } else {
                log.error("해당 날짜에 해당하는 모임이 없습니다.");
            }
        }else {
            log.error("해당 날짜에 해당하는 모임이 없습니다.");
        }
    }

    public double getAttendanceRate(String userid, LocalDate date) {
        User user = userRepository.findByUserid(userid);
        List<Attendances> attendancesList = attendanceRepository.findByUserAndDateBefore(user, LocalDate.of(2024,6,10));
        List<Attendances> totalAttendancesList = attendanceRepository.findByUser(user);
        double totalCount = totalAttendancesList.size();

        long countOfTrueAttendances = attendancesList.stream()
                .filter(attendance -> attendance.getAttendance().equals(true)) // 출석이 true인 항목들만 필터링
                .count();

        double percent = ((countOfTrueAttendances / totalCount) * 100);
        percent = Math.round(percent * 100.0) / 100.0;
        return percent;
    }

    public List<Attendances> getAllAttendanceRecord(String userid){
        User user = userRepository.findByUserid(userid);
        List<Attendances> allAttendances = attendanceRepository.findAllByUser(user);
        return ResponseEntity.ok(allAttendances).getBody();
    }
}
