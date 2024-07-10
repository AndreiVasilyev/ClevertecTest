package ru.clevertec.check.dao;


import ru.clevertec.check.dao.db.DbProductDao;
import ru.clevertec.check.dao.db.impl.DbProductDaoImpl;
import ru.clevertec.check.dao.file.CsvFileReader;
import ru.clevertec.check.dao.file.CsvFileWriter;
import ru.clevertec.check.dao.file.impl.CsvFileReaderImpl;
import ru.clevertec.check.dao.file.impl.CsvFileWriterImpl;

public class DaoProvider {
    private static final DaoProvider instance = new DaoProvider();
    private final CsvFileReader csvFileReader;
    private final CsvFileWriter csvFileWriter;
    private final DbProductDao dbProductDao;

    private DaoProvider() {
        csvFileReader = new CsvFileReaderImpl();
        csvFileWriter = new CsvFileWriterImpl();
        dbProductDao = new DbProductDaoImpl();
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

    public DbProductDao getDbProductDao() {
        return dbProductDao;
    }
}
