package ru.clevertec.check.view;

import ru.clevertec.check.entity.Check;
import ru.clevertec.check.entity.DiscountCard;
import ru.clevertec.check.entity.Product;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import static ru.clevertec.check.view.ViewConstantRepository.*;


public class FileViewFormatter {

    public void formatDateTime(List<String> lines, Check check) {
        String dateTimeValue = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN).format(check.getDateTime());
        String dateTimeLine = String.format(DATE_TIME_TEMPLATE, dateTimeValue);
        lines.add(dateTimeLine);
    }

    public void formatProducts(List<String> lines, Check check) {
        lines.add(PRODUCTS_TITLE);
        HashMap<Product, Double> totals = check.getTotals();
        HashMap<Product, Double> discounts = check.getDiscounts();
        check.getProducts()
                .forEach((key, value) -> {
                    String productLine = String.format(PRODUCTS_TEMPLATE, value, key.getDescription(), key.getPrice(), discounts.get(key), totals.get(key));
                    lines.add(productLine);
                });
        lines.add(NEW_LINE);
    }

    public void formatDiscountCard(List<String> lines, DiscountCard discountCard) {
        String discountLine = String.format(DISCOUNT_CARD_TEMPLATE, discountCard.getNumber(), discountCard.getDiscountAmount());
        lines.add(discountLine);
    }

    public void formatTotalValues(List<String> lines, Check check) {
        Double totalPrice = check.getTotalPrice();
        Double totalDiscount = check.getTotalDiscount();
        String totalLine = String.format(TOTAL_VALUES_TEMPLATE, totalPrice, totalDiscount, totalPrice - totalDiscount);
        lines.add(totalLine);
    }

    public void formatError(List<String> lines, Exception exception) {
        String errorMessage = String.format(ERROR_TEMPLATE, exception.getMessage());
        lines.add(errorMessage);
    }
}
