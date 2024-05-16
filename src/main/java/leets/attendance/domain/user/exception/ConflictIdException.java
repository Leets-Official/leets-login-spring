package leets.attendance.domain.user.exception;

import leets.attendance.global.error.ErrorCode;
import leets.attendance.global.error.ServiceException;


public class ConflictIdException extends ServiceException {
    public ConflictIdException() {
        super(ErrorCode.ID_CONFLICT.getHttpStatus(),ErrorCode.ID_CONFLICT.getMessage());
    }
}
