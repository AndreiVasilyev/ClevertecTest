package ru.clevertec.check;

import ru.clevertec.check.entity.Check;
import ru.clevertec.check.exception.BadRequestException;
import ru.clevertec.check.exception.ExceptionHandler;
import ru.clevertec.check.exception.InternalServerException;
import ru.clevertec.check.exception.NotEnoughMoneyException;

import java.util.Arrays;

public class CheckRunner {
    public static void main(String[] args) {
        try {
            Arrays.stream(args).forEach(System.out::println) ;
            CheckOperator checkOperator = CheckOperator.getInstance();
            Check check = checkOperator.create(args);
            checkOperator.save(check);
            checkOperator.printToConsole(check);
        } catch (BadRequestException | NotEnoughMoneyException | InternalServerException e) {
            ExceptionHandler.getInstance().handle(e, args);
        }
    }
}
