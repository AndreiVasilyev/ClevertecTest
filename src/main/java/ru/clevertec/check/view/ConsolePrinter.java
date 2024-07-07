package main.java.ru.clevertec.check.view;

import main.java.ru.clevertec.check.entity.Check;
import main.java.ru.clevertec.check.entity.Product;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import static main.java.ru.clevertec.check.view.ViewConstantRepository.*;

public class ConsolePrinter {

    private static ConsolePrinter instance;

    private ConsolePrinter() {
    }

    public static ConsolePrinter getInstance() {
        if (instance == null) {
            instance = new ConsolePrinter();
        }
        return instance;
    }

    public void printCheckToConsole(Check check) {
        System.out.printf("%12s%10s%n", "Date", "Time");
        String dateTime = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN).format(check.getDateTime());
        String[] dateTimeValues = dateTime.split(";");
        System.out.printf("%12s%10s\n\n", dateTimeValues[0], dateTimeValues[1]);
        System.out.printf("%5s%20s%8s%10s%8s", "QTY", "DESCRIPTION", "PRICE", "DISCOUNT", "TOTAL");
        HashMap<Product, Double> totals = check.getTotals();
        HashMap<Product, Double> discounts = check.getDiscounts();
        check.getProducts()
                .forEach((key, value) -> System.out.printf("\n%5d%20s%7.2f%9.2f%9.2f", value, key.getDescription(), key.getPrice(), discounts.get(key), totals.get(key)));
        if (check.getDiscountCard() != null) {
            System.out.printf("\n\n%15s%25s\n", "DISCOUNT CARD", "DISCOUNT PERCENTAGE");
            System.out.printf("%12d%20d%%", check.getDiscountCard().getNumber(), check.getDiscountCard().getDiscountAmount());
        }
        System.out.printf("\n\n%13s%20s%25s\n", "TOTAL PRICE", "TOTAL DISCOUNT", "TOTAL WITH DISCOUNT");
        System.out.printf("%9.2f$%17.2f$%23.2f$\n\n", check.getTotalPrice(), check.getTotalDiscount(), check.getTotalPrice() - check.getTotalDiscount());
    }

    public void printErrorToConsole(Exception exception) {
        System.out.printf("\n%s\n%s\n\n", "ERROR", exception.getMessage());
    }
}
