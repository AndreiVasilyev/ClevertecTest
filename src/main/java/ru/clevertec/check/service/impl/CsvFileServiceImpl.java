package ru.clevertec.check.service.impl;

import ru.clevertec.check.dao.DaoProvider;
import ru.clevertec.check.dao.file.CsvFileReader;
import ru.clevertec.check.dao.file.CsvFileWriter;
import ru.clevertec.check.entity.Check;
import ru.clevertec.check.entity.DiscountCard;
import ru.clevertec.check.entity.Product;
import ru.clevertec.check.entity.Stock;
import ru.clevertec.check.exception.InternalServerException;
import ru.clevertec.check.mapper.DiscountCardMapper;
import ru.clevertec.check.mapper.FilePathMapper;
import ru.clevertec.check.mapper.ProductMapper;
import ru.clevertec.check.service.CsvFileService;
import ru.clevertec.check.view.FileViewFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.clevertec.check.service.ServiceConstantRepository.*;


public class CsvFileServiceImpl implements CsvFileService {

    @Override
    public Stock readStock(String[] args) throws InternalServerException {
        String pathToFile = FilePathMapper.mapPathToFile(args);
        CsvFileReader csvFileReader = DaoProvider.getInstance().getCsvReader();
        List<List<String>> lines = csvFileReader.read(pathToFile);
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
    public void saveCheck(Check check, String saveToFile) throws InternalServerException {
        FileViewFormatter fileViewFormatter = new FileViewFormatter();
        CsvFileWriter csvFileWriter = DaoProvider.getInstance().getCsvFileWriter();
        List<String> lines = new ArrayList<>();
        fileViewFormatter.formatDateTime(lines, check);
        fileViewFormatter.formatProducts(lines, check);
        if (check.getDiscountCard() != null) {
            fileViewFormatter.formatDiscountCard(lines, check.getDiscountCard());
        }
        fileViewFormatter.formatTotalValues(lines, check);
        csvFileWriter.write(lines, saveToFile);
    }

    @Override
    public void saveError(Exception e, String saveToFile) throws InternalServerException {
        FileViewFormatter fileViewFormatter = new FileViewFormatter();
        CsvFileWriter csvFileWriter = DaoProvider.getInstance().getCsvFileWriter();
        List<String> lines = new ArrayList<>();
        fileViewFormatter.formatError(lines, e);
        csvFileWriter.write(lines, saveToFile);
    }

}
