package main.java.ru.clevertec.check.service.impl;

import main.java.ru.clevertec.check.dao.DaoProvider;
import main.java.ru.clevertec.check.dao.file.CsvReader;
import main.java.ru.clevertec.check.entity.DiscountCard;
import main.java.ru.clevertec.check.entity.Product;
import main.java.ru.clevertec.check.entity.Stock;
import main.java.ru.clevertec.check.mapper.DiscountCardMapper;
import main.java.ru.clevertec.check.mapper.ProductMapper;
import main.java.ru.clevertec.check.service.CsvReaderService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static main.java.ru.clevertec.check.service.ServiceConstantRepository.*;

public class CsvReaderServiceImpl implements CsvReaderService {
    @Override
    public Stock readStock() {
        CsvReader csvReader = DaoProvider.getInstance().getCsvReader();
        List<List<String>> lines = csvReader.read(CSV_PRODUCT_FILE_PATH);
        lines.removeFirst();
        Map<Product, Integer> products = lines.stream()
                .collect(Collectors.toMap(ProductMapper::map, val -> Integer.parseInt(val.get(3))));
        return new Stock(products);
    }


    @Override
    public List<DiscountCard> readAllDiscountCards() {
        CsvReader csvReader = DaoProvider.getInstance().getCsvReader();
        List<List<String>> lines = csvReader.read(CSV_DISCOUNT_CARD_FILE_PATH);
        lines.removeFirst();
        return lines.stream()
                .map(DiscountCardMapper::map)
                .toList();
    }


}
