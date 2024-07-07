package main.java.ru.clevertec.check.dao.file;

import main.java.ru.clevertec.check.exception.InternalServerException;

import java.util.List;

public interface CsvFileWriter {
    void write(List<String> lines, String pathToFile) throws InternalServerException;
}
