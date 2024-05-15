package leets.global.exception;

import org.springframework.http.HttpStatus;

public class LeetsException extends RuntimeException {

    private HttpStatus httpStatus;

    public LeetsException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
