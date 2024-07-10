package ru.clevertec.check.mapper;


import ru.clevertec.check.entity.DiscountCard;

import java.util.List;

public class DiscountCardMapper {

    public static DiscountCard map(List<String> values) {
        DiscountCard discountCard = new DiscountCard();
        discountCard.setId(Long.parseLong(values.get(0)));
        discountCard.setNumber(Integer.parseInt(values.get(1)));
        discountCard.setDiscountAmount(Integer.parseInt(values.get(2)));
        return discountCard;
    }
}
