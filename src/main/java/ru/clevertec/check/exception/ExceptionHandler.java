package main.java.ru.clevertec.check.exception;

import main.java.ru.clevertec.check.service.CsvFileService;
import main.java.ru.clevertec.check.service.ServiceProvider;
import main.java.ru.clevertec.check.view.ConsolePrinter;

public class ExceptionHandler {

    private static ExceptionHandler instance = new ExceptionHandler();

    private ExceptionHandler() {
    }

    public static ExceptionHandler getInstance() {
        return instance;
    }

    public void handle(Exception e) {
        CsvFileService csvFileService= ServiceProvider.getInstance().getCsvFileService();
        try {
            csvFileService.saveError(e);
            ConsolePrinter.getInstance().printErrorToConsole(e);
        } catch (InternalServerException ex) {
            throw new RuntimeException(ex);
        }
    }
}
