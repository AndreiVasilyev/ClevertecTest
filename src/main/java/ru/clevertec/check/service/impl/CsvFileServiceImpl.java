package main.java.ru.clevertec.check.service.impl;

import main.java.ru.clevertec.check.dao.DaoProvider;
import main.java.ru.clevertec.check.dao.file.CsvFileReader;
import main.java.ru.clevertec.check.dao.file.CsvFileWriter;
import main.java.ru.clevertec.check.entity.Check;
import main.java.ru.clevertec.check.entity.DiscountCard;
import main.java.ru.clevertec.check.entity.Product;
import main.java.ru.clevertec.check.entity.Stock;
import main.java.ru.clevertec.check.exception.InternalServerException;
import main.java.ru.clevertec.check.mapper.DiscountCardMapper;
import main.java.ru.clevertec.check.mapper.ProductMapper;
import main.java.ru.clevertec.check.service.CsvFileService;
import main.java.ru.clevertec.check.view.FileViewFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static main.java.ru.clevertec.check.service.ServiceConstantRepository.*;

public class CsvFileServiceImpl implements CsvFileService {

    @Override
    public Stock readStock() throws InternalServerException {
        CsvFileReader csvFileReader = DaoProvider.getInstance().getCsvReader();
        List<List<String>> lines = csvFileReader.read(CSV_PRODUCT_FILE_PATH);
        lines.removeFirst();
        Map<Product, Integer> products = lines.stream()
                .collect(Collectors.toMap(ProductMapper::map, val -> Integer.parseInt(val.get(3))));
        return new Stock(products);
    }

    @Override
    public List<DiscountCard> readAllDiscountCards() throws InternalServerException {
        CsvFileReader csvFileReader = DaoProvider.getInstance().getCsvReader();
        List<List<String>> lines = csvFileReader.read(CSV_DISCOUNT_CARD_FILE_PATH);
        lines.removeFirst();
        return lines.stream()
                .map(DiscountCardMapper::map)
                .toList();
    }

    @Override
    public void saveCheck(Check check) throws InternalServerException {
        FileViewFormatter fileViewFormatter = new FileViewFormatter();
        CsvFileWriter csvFileWriter = DaoProvider.getInstance().getCsvFileWriter();
        List<String> lines = new ArrayList<>();
        fileViewFormatter.formatDateTime(lines, check);
        fileViewFormatter.formatProducts(lines, check);
        if (check.getDiscountCard() != null) {
            fileViewFormatter.formatDiscountCard(lines, check.getDiscountCard());
        }
        fileViewFormatter.formatTotalValues(lines, check);
        csvFileWriter.write(lines, CSV_RESULT_FILE_PATH);
    }

    @Override
    public void saveError(Exception e) throws InternalServerException {
        FileViewFormatter fileViewFormatter = new FileViewFormatter();
        CsvFileWriter csvFileWriter = DaoProvider.getInstance().getCsvFileWriter();
        List<String> lines = new ArrayList<>();
        fileViewFormatter.formatError(lines, e);
        csvFileWriter.write(lines, CSV_RESULT_FILE_PATH);
    }

}
