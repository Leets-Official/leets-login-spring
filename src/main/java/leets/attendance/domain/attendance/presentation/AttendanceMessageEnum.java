package leets.attendance.domain.attendance.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AttendanceMessageEnum {
    UPDATE_SUCCESS("출석 갱신에 성공하였습니다."),
    GET_SUCCESS("목록조회에 성공하였습니다");
    private String message;
}
