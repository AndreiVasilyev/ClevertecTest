package ru.clevertec.check.exception;


import ru.clevertec.check.mapper.FilePathMapper;
import ru.clevertec.check.service.CsvFileService;
import ru.clevertec.check.service.ServiceProvider;
import ru.clevertec.check.view.ConsolePrinter;

import static ru.clevertec.check.service.ServiceConstantRepository.*;

public class ExceptionHandler {

    private static final ExceptionHandler instance = new ExceptionHandler();

    private ExceptionHandler() {
    }

    public static ExceptionHandler getInstance() {
        return instance;
    }

    public void handle(Exception e, String[] args) {
        CsvFileService csvFileService = ServiceProvider.getInstance().getCsvFileService();
        try {
            String saveToFile = FilePathMapper.mapSaveToFile(args);
            if (saveToFile.isBlank()) {
                saveToFile = CSV_RESULT_FILE_PATH;
            }
            csvFileService.saveError(e, saveToFile);
            ConsolePrinter.getInstance().printErrorToConsole(e);
        } catch (InternalServerException ex) {
            throw new RuntimeException(ex);
        }
    }
}
