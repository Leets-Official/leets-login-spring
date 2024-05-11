package leets.attendance.domain.attendance.application;


import leets.attendance.domain.attendance.Repository.AttendanceRepository;
import leets.attendance.domain.attendance.domain.Attendance;
import leets.attendance.domain.attendance.exception.InvalidDayException;
import leets.attendance.domain.attendance.presentation.AttendanceMessageEnum;
import leets.attendance.domain.user.application.UserService;
import leets.attendance.domain.user.domain.User;
import leets.attendance.domain.user.repository.UserRepository;
import leets.attendance.global.DateEnum;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;

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

    public String updateAttendance(Authentication authentication) {
        String id = authentication.getName();
        User user = userRepository.findById(id).get();
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime test = LocalDateTime.of(2024,5,9,19,0);
        if (!(test.getDayOfWeek() == DayOfWeek.THURSDAY)) {
            throw new InvalidDayException();
        }
        DateEnum dateEnum = isValidWeek(test);
        String weekNumberStr = dateEnum.name().replace("WEEK_", "");
        Attendance attendance = attendanceRepository.findByWeekAndUser(Integer.parseInt(weekNumberStr),user).get();
        Attendance newAttendance = Attendance.builder()
                .attendanceId(attendance.getAttendanceId())
                .isAttend(true)
                .user(user)
                .week(attendance.getWeek())
                .build();
        attendanceRepository.save(newAttendance);
        return AttendanceMessageEnum.UPDATE_SUCCESS.getMessage();
    }

    public DateEnum isValidWeek(LocalDateTime today) {
        try {
            if (today.getHour() != 19) {
                throw new InvalidDayException();
            }
            LocalDate validDate = today.with(DayOfWeek.THURSDAY).toLocalDate();
            return DateEnum.getByDate(validDate);
        } catch (IllegalArgumentException e) {
            throw new InvalidDayException();
        }
    }
}
