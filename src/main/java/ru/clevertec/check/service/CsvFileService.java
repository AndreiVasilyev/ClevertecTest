package main.java.ru.clevertec.check.service;

import main.java.ru.clevertec.check.entity.Check;
import main.java.ru.clevertec.check.entity.DiscountCard;
import main.java.ru.clevertec.check.entity.Stock;
import main.java.ru.clevertec.check.exception.InternalServerException;

import java.util.List;

public interface CsvFileService {

    Stock readStock() throws InternalServerException;

    List<DiscountCard> readAllDiscountCards() throws InternalServerException;

    void saveCheck(Check check) throws InternalServerException;

    void saveError(Exception e) throws InternalServerException;
}
