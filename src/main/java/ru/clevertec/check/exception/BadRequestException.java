package ru.clevertec.check.exception;


import static ru.clevertec.check.exception.ExceptionMessageRepository.BAD_REQUEST;

public class BadRequestException extends Exception {

    public BadRequestException() {
        super(BAD_REQUEST);
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }
}
