package main.java.ru.clevertec.check.service;

import main.java.ru.clevertec.check.entity.DiscountCard;
import main.java.ru.clevertec.check.entity.Stock;

import java.util.List;

public interface CsvReaderService {
    Stock readStock();

    List<DiscountCard> readAllDiscountCards();
}
