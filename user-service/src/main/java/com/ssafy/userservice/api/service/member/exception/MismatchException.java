package com.ssafy.userservice.api.service.member.exception;

public class MismatchException extends IllegalArgumentException {

    public MismatchException() {
    }

    public MismatchException(String s) {
        super(s);
    }

    public MismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public MismatchException(Throwable cause) {
        super(cause);
    }
}
