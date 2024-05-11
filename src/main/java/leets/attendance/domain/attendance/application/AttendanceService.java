package leets.attendance.domain.attendance.application;


import leets.attendance.domain.attendance.Repository.AttendanceRepository;
import leets.attendance.domain.attendance.domain.Attendance;
import leets.attendance.domain.user.domain.User;
import leets.attendance.global.DateEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    public void createInitialAttendance(User user) {
        for (int i = 1; i <= 8; i++) {
            Attendance attendance = Attendance.builder()
                    .isAttend(false)
                    .week(i)
                    .attendanceTime(DateEnum.getByWeekNumber(i).getStartDate())
                    .user(user)
                    .build();
            attendanceRepository.save(attendance);
        }
    }
}
