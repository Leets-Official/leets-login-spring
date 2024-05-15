package leets.attendance.domain.attendance.service;

import java.security.Principal;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import leets.attendance.domain.attendance.entity.Attendance;
import leets.attendance.domain.attendance.entity.AttendanceStatus;
import leets.attendance.domain.attendance.model.response.AttendanceInfoResponse;
import leets.attendance.domain.attendance.model.response.AttendanceRatesResponse;
import leets.attendance.domain.attendance.repository.AttendanceRepository;
import leets.attendance.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceService {

  private final AttendanceRepository attendanceRepository;

  public AttendanceInfoResponse getAttendanceInfo(Principal connectedUser) {
    Member member = (Member) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
    LocalDate currentDate = LocalDate.now();

    return AttendanceInfoResponse.of(member, currentDate);
  }

  public AttendanceRatesResponse getAttendanceRates(Principal connectedUser) {
    Member member = (Member) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
    LocalDate currentDate = LocalDate.now();

    AttendanceInfoResponse infoResponse = AttendanceInfoResponse.of(member, currentDate);

    List<Attendance> attendanceList = attendanceRepository.findAllByMember(member);
    List<AttendanceStatus> attendanceStatuses = attendanceList.stream().map(Attendance::getAttendanceStatus).toList();

    double rate = getAttendanceRate(attendanceStatuses);

    return AttendanceRatesResponse.of(infoResponse, attendanceStatuses, rate);
  }

  public void attend(Principal connectedUser) {
    Member member = (Member) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

    if (!verifyThursday()) {
      throw new DateTimeException("오늘은 동아리 활동 일이 아닙니다.");
    }

    Attendance attendance = attendanceRepository.findByMemberAndDate(member, getThursday()).orElseThrow();
    attendance.attend();
    attendanceRepository.save(attendance);
  }

  private double getAttendanceRate(List<AttendanceStatus> attendanceStatuses) {
    double rate;

    int totalAttendanceCount = 0;
    int attendanceCount = 0;
    for (AttendanceStatus attendanceStatus : attendanceStatuses) {
      if (attendanceStatus == AttendanceStatus.ATTENDANCE) {
        totalAttendanceCount++;
        attendanceCount++;
      } else if (attendanceStatus == AttendanceStatus.ABSENCE) {
        totalAttendanceCount++;
      }
    }

    rate = totalAttendanceCount == 0 ? 0 : (double) attendanceCount / totalAttendanceCount * 100;
    return rate;
  }

  private LocalDate getThursday() {
    LocalDate currentDate = LocalDate.now();
    return currentDate.with(DayOfWeek.THURSDAY);
  }

  private boolean verifyThursday() {
    LocalDate currentDate = LocalDate.now();
    return currentDate.equals(getThursday());
  }
}
