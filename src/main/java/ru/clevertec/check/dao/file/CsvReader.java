package main.java.ru.clevertec.check.dao.file;

import java.util.List;

public interface CsvReader {
    List<List<String>> read(String filePath);
}
