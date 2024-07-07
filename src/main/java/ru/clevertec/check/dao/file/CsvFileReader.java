package main.java.ru.clevertec.check.dao.file;

import main.java.ru.clevertec.check.exception.InternalServerException;

import java.util.List;

public interface CsvFileReader {
    List<List<String>> read(String filePath) throws InternalServerException;
}
