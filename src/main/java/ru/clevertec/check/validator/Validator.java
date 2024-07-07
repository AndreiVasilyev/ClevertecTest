package main.java.ru.clevertec.check.validator;

import main.java.ru.clevertec.check.entity.Check;
import main.java.ru.clevertec.check.entity.Order;

import java.util.Arrays;

import static main.java.ru.clevertec.check.mapper.MapperConstantRepository.*;

public class Validator {

    private static Validator instance;

    public static Validator getInstance() {
        if (instance == null) {
            instance = new Validator();
        }
        return instance;
    }

    public boolean isInputDataValid(String[] data) {
        if (data == null || data.length == 0) return false;
        if (!data[0].matches(PRODUCT_PARAMETER_REGEX)) return false;
        if (!String.join(ARGUMENTS_SEPARATOR, data).contains(BALANCE_DEBIT_CARD_PARAM_PREFIX)) return false;
        for (String parameter : data) {
            if (parameter.trim().startsWith(BALANCE_DEBIT_CARD_PARAM_PREFIX)) {
                String[] parsedParam = parameter.split(PARAMETERS_SEPARATOR);
                if (parsedParam.length != 2 || Double.parseDouble(parsedParam[1]) <= 0) return false;
            }
            if (parameter.trim().startsWith(DISCOUNT_CARD_PARAM_PREFIX)) {
                String[] parsedParam = parameter.split(PARAMETERS_SEPARATOR);
                if (parsedParam.length != 2 || !parsedParam[1].matches(DISCOUNT_CARD_NUMBER_REGEX)) return false;
            }
        }
        return true;
    }

    public boolean isEnoughBalance(Check check, Order order) {
        return (check.getTotalPrice() - check.getTotalDiscount()) > order.getBalanceDebitCard();
    }
}
