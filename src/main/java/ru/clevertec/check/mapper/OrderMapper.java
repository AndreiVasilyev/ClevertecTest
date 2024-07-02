package main.java.ru.clevertec.check.mapper;

import main.java.ru.clevertec.check.entity.Order;

import java.util.Arrays;
import java.util.HashMap;

public class OrderMapper {
    public Order map(String[] values) {
        Order order = new Order();
        order.setProducts(new HashMap<>());
        Arrays.stream(values).forEach(value -> {
            if (value.matches("\\d.+")) {
                String[] productValues = value.trim().split("-");
                order.getProducts().merge(Long.parseLong(productValues[0]), Integer.parseInt(productValues[1]), Integer::sum);
            } else {
                String[] productValues = value.trim().split("=");
                if ("discountCard".equalsIgnoreCase(productValues[0])) {
                    order.setDiscountCardNumber(Integer.parseInt(productValues[1]));
                } else if ("balanceDebitCard".equalsIgnoreCase(productValues[0])) {
                    order.setBalanceDebitCard(Double.parseDouble(productValues[1]));
                }
            }
        });
        return order;
    }
}
