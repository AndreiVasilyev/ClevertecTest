package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.entity.Check;
import main.java.ru.clevertec.check.exception.BadRequestException;
import main.java.ru.clevertec.check.exception.ExceptionHandler;
import main.java.ru.clevertec.check.exception.InternalServerException;
import main.java.ru.clevertec.check.exception.NotEnoughMoneyException;

public class CheckRunner {
    public static void main(String[] args) {
        try {
            CheckOperator checkOperator = CheckOperator.getInstance();
            Check check = checkOperator.create(args);
            checkOperator.save(check);
            checkOperator.printToConsole(check);
        } catch (BadRequestException | NotEnoughMoneyException | InternalServerException e) {
            ExceptionHandler.getInstance().handle(e,args);
        }
    }
}
