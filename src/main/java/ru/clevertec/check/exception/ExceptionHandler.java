package main.java.ru.clevertec.check.exception;

import main.java.ru.clevertec.check.mapper.FilePathMapper;
import main.java.ru.clevertec.check.service.CsvFileService;
import main.java.ru.clevertec.check.service.ServiceProvider;
import main.java.ru.clevertec.check.view.ConsolePrinter;

import static main.java.ru.clevertec.check.service.ServiceConstantRepository.CSV_RESULT_FILE_PATH;

public class ExceptionHandler {

    private static ExceptionHandler instance = new ExceptionHandler();

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
