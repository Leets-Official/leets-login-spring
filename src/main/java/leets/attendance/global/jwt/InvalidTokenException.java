package leets.attendance.global.jwt;

import leets.attendance.global.error.ErrorCode;
import leets.attendance.global.error.ServiceException;

public class InvalidTokenException extends ServiceException {
    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN.getHttpStatus());
    }
}
