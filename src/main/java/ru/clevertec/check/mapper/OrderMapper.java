package ru.clevertec.check.mapper;

import ru.clevertec.check.entity.Order;

import java.util.Arrays;
import java.util.HashMap;

import static ru.clevertec.check.mapper.MapperConstantRepository.*;


public class OrderMapper {
    public Order map(String[] values) {
        Order order = new Order();
        order.setProducts(new HashMap<>());
        Arrays.stream(values).forEach(value -> {
            if (value.matches(FIRST_PARAMETER_REGEX)) {
                String[] productValues = value.trim().split(FIRST_PARAMETER_SEPARATOR);
                order.getProducts().merge(Long.parseLong(productValues[0]), Integer.parseInt(productValues[1]), Integer::sum);
            } else {
                String[] productValues = value.trim().split(PARAMETERS_SEPARATOR);
                if (DISCOUNT_CARD_PARAM_PREFIX.equalsIgnoreCase(productValues[0])) {
                    order.setDiscountCardNumber(Integer.parseInt(productValues[1]));
                } else if (BALANCE_DEBIT_CARD_PARAM_PREFIX.equalsIgnoreCase(productValues[0])) {
                    order.setBalanceDebitCard(Double.parseDouble(productValues[1]));
                }
            }
        });
        return order;
    }
}
