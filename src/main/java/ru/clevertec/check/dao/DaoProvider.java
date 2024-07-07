package main.java.ru.clevertec.check.dao;

import main.java.ru.clevertec.check.dao.file.CsvFileReader;
import main.java.ru.clevertec.check.dao.file.CsvFileWriter;
import main.java.ru.clevertec.check.dao.file.impl.CsvFileReaderImpl;
import main.java.ru.clevertec.check.dao.file.impl.CsvFileWriterImpl;

public class DaoProvider {
    private static final DaoProvider instance = new DaoProvider();
    private final CsvFileReader csvFileReader;
    private final CsvFileWriter csvFileWriter;

    private DaoProvider() {
        csvFileReader = new CsvFileReaderImpl();
        csvFileWriter = new CsvFileWriterImpl();
    }

    public static DaoProvider getInstance() {
        return instance;
    }

    public CsvFileReader getCsvReader() {
        return csvFileReader;
    }

    public CsvFileWriter getCsvFileWriter() {
        return csvFileWriter;
    }
}
