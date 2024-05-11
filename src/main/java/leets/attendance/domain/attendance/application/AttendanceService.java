package leets.attendance.domain.attendance.application;


import leets.attendance.domain.attendance.Repository.AttendanceRepository;
import leets.attendance.domain.attendance.domain.Attendance;
import leets.attendance.domain.attendance.dto.AttendanceInfo;
import leets.attendance.domain.attendance.dto.AttendanceResponseDto;
import leets.attendance.domain.attendance.exception.InvalidDayException;
import leets.attendance.domain.attendance.presentation.AttendanceMessageEnum;
import leets.attendance.domain.user.application.UserService;
import leets.attendance.domain.user.domain.User;
import leets.attendance.domain.user.repository.UserRepository;
import leets.attendance.global.DateEnum;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        if (!(date.getDayOfWeek() == DayOfWeek.THURSDAY)) {
            throw new InvalidDayException();
        }
        DateEnum dateEnum = isValidWeek(date);
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

    public AttendanceResponseDto getAttendances(Authentication authentication) {
        User user = userRepository.findById(authentication.getName()).get();
        List<Attendance> attendances = attendanceRepository.findByUser(user);
        List<AttendanceInfo> attendanceInfos = attendances.stream()
                .map(a -> new AttendanceInfo(a.getWeek(), a.getIsAttend()))
                .collect(Collectors.toList());

        return AttendanceResponseDto.builder()
                .attendanceList(attendanceInfos)
                .name(user.getName())
                .date(LocalDate.now())
                .build();
    }

    public int dateToInt(LocalDateTime date) {
        LocalDate localDate = date.with(DayOfWeek.THURSDAY).toLocalDate();
        String weekNumberStr = DateEnum.getByDate(localDate).name().replace("WEEK_", "");

        return Integer.parseInt(weekNumberStr);
    }

    public String getAttendanceRates(Authentication authentication) {
        User user = userRepository.findById(authentication.getName()).get();
        LocalDateTime date = LocalDateTime.now();
        double trueValue = attendanceRepository.findByUserAndIsAttend(user,true).size();
        double dateSize = attendanceRepository.findByUser(user).stream().filter(a -> a.getWeek() <= dateToInt(date)).toList().size();

        return (trueValue / dateSize) * 100 +"%";
    }
}
