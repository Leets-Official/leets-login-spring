package leets.attendance.domain.user.exception;

import leets.attendance.global.error.ErrorCode;
import leets.attendance.global.error.ServiceException;


public class UserConflictException extends ServiceException {
    public UserConflictException() {
        super(ErrorCode.USER_CONFLICT.getHttpStatus(),ErrorCode.USER_CONFLICT.getMessage());
    }
}
