package main.java.ru.clevertec.check.validator;

import java.util.Arrays;

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
        if (!data[0].matches("\\d+-\\d+")) return false;
        if (!String.join(" ", data).contains("balanceDebitCard")) return false;
        for (String parameter : data) {
            if (parameter.trim().startsWith("balanceDebitCard")) {
                String[] parsedParam = parameter.split("=");
                if (parsedParam.length != 2 || Double.parseDouble(parsedParam[1]) <= 0) return false;
            }
            if (parameter.trim().startsWith("discountCard")) {
                String[] parsedParam = parameter.split("=");
                if (parsedParam.length != 2 || !parsedParam[1].matches("\\d{4}")) return false;
            }
        }
        return true;
    }
}
