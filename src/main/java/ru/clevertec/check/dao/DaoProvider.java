package main.java.ru.clevertec.check.dao;

import main.java.ru.clevertec.check.dao.file.CsvReader;
import main.java.ru.clevertec.check.dao.file.impl.CsvReaderImpl;

public class DaoProvider {
    private static final DaoProvider instance = new DaoProvider();
    private final CsvReader csvReader;

    private DaoProvider() {
        csvReader = new CsvReaderImpl();
    }

    public static DaoProvider getInstance() {
        return instance;
    }

    public CsvReader getCsvReader() {
        return csvReader;
    }

}
