package ru.clevertec.check.service;


import ru.clevertec.check.service.impl.CsvFileServiceImpl;
import ru.clevertec.check.service.impl.DbProductServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();
    private final DbProductService dbProductService;
    private final CsvFileService csvFileService;

    private ServiceProvider() {
        csvFileService = new CsvFileServiceImpl();
        dbProductService = new DbProductServiceImpl();
    }

    public static ServiceProvider getInstance() {
        return instance;
    }

    public CsvFileService getCsvFileService() {
        return csvFileService;
    }

    public DbProductService getDbProductService() {
        return dbProductService;
    }
}
