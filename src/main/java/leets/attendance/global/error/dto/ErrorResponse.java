package leets.attendance.global.error.dto;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getHttpStatus().value();
        this.error = errorCode.getHttpStatus().name();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}
