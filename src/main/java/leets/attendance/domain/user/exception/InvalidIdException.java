package leets.attendance.domain.user.exception;

import leets.attendance.global.error.ErrorCode;
import leets.attendance.global.error.ServiceException;

public class InvalidIdException extends ServiceException {
    public InvalidIdException() {
        super(ErrorCode.INVALID_ID.getHttpStatus(),ErrorCode.INVALID_ID.getMessage());
    }
}
