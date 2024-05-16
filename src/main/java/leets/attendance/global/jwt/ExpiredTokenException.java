package leets.attendance.global.jwt;

import leets.attendance.global.error.ErrorCode;
import leets.attendance.global.error.ServiceException;

public class ExpiredTokenException extends ServiceException {
    public ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN.getHttpStatus());
    }
}
