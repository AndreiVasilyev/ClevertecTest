package main.java.ru.clevertec.check.exception;

import static main.java.ru.clevertec.check.exception.ExceptionMessageRepository.INTERNAL_SERVER_ERROR;

public class InternalServerException extends Exception {
    public InternalServerException() {
        super(INTERNAL_SERVER_ERROR);
    }

    public InternalServerException(String message) {
        super(message);
    }

    public InternalServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerException(Throwable cause) {
        super(cause);
    }
}
