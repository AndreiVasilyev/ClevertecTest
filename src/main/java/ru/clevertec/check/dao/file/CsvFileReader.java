package ru.clevertec.check.dao.file;


import ru.clevertec.check.exception.InternalServerException;

import java.util.List;

public interface CsvFileReader {
    List<List<String>> read(String filePath) throws InternalServerException;
}
