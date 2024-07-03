package main.java.ru.clevertec.check.exception;

import static main.java.ru.clevertec.check.exception.ExceptionMessageRepository.NOT_ENOUGH_MONEY;

public class NotEnoughMoneyException extends Exception{
    public NotEnoughMoneyException() {
        super(NOT_ENOUGH_MONEY);
    }

    public NotEnoughMoneyException(String message) {
        super(message);
    }

    public NotEnoughMoneyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughMoneyException(Throwable cause) {
        super(cause);
    }
}
