package main.java.ru.clevertec.check.service;

import main.java.ru.clevertec.check.service.impl.CsvReaderServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();
    private final CsvReaderService csvReaderService;

    private ServiceProvider() {
        csvReaderService = new CsvReaderServiceImpl();
    }

    public static ServiceProvider getInstance() {
        return instance;
    }

    public CsvReaderService getCsvReaderService() {
        return csvReaderService;
    }
}
