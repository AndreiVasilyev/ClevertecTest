package ru.clevertec.check.service;


import ru.clevertec.check.entity.Check;
import ru.clevertec.check.entity.DiscountCard;
import ru.clevertec.check.entity.Stock;
import ru.clevertec.check.exception.InternalServerException;

import java.util.List;

public interface CsvFileService {

    Stock readStock(String[] args) throws InternalServerException;

    List<DiscountCard> readAllDiscountCards() throws InternalServerException;

    void saveCheck(Check check, String saveToFile) throws InternalServerException;

    void saveError(Exception e, String saveToFile) throws InternalServerException;
}
