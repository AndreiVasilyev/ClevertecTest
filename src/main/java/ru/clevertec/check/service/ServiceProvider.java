package main.java.ru.clevertec.check.service;

import main.java.ru.clevertec.check.service.impl.CsvFileServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();
    private final CsvFileService csvFileService;

    private ServiceProvider() {
        csvFileService = new CsvFileServiceImpl();
    }

    public static ServiceProvider getInstance() {
        return instance;
    }

    public CsvFileService getCsvFileService() {
        return csvFileService;
    }
}
