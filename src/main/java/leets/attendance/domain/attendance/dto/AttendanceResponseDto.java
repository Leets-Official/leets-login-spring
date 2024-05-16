package leets.attendance.domain.attendance.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceResponseDto {
    private String name;
    private LocalDate date;
    private List<AttendanceInfo> attendanceList;

}

