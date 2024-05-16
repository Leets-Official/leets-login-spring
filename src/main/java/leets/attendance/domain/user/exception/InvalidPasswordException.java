package leets.attendance.domain.user.exception;

import leets.attendance.global.error.ErrorCode;
import leets.attendance.global.error.ServiceException;

public class InvalidPasswordException extends ServiceException {
    public InvalidPasswordException() {
        super(ErrorCode.INVALID_PASSWORD.getHttpStatus(),ErrorCode.INVALID_PASSWORD.getMessage());
    }
}
