package ru.clevertec.check.service;

import ru.clevertec.check.entity.Stock;
import ru.clevertec.check.exception.BadRequestException;
import ru.clevertec.check.exception.InternalServerException;

public interface DbProductService {
    Stock getStock(String[] args) throws BadRequestException, InternalServerException;
}
