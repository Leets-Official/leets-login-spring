package leets.attendance.domain.attendance.exception;

import leets.attendance.global.error.ErrorCode;
import leets.attendance.global.error.ServiceException;

public class InvalidDayException extends ServiceException {
    public InvalidDayException(){
        super(ErrorCode.INVALID_TIME.getHttpStatus(),ErrorCode.INVALID_TIME.getMessage());
    }
}
