package main.java.ru.clevertec.check.dao.file.impl;

import main.java.ru.clevertec.check.dao.file.CsvReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static main.java.ru.clevertec.check.dao.DaoConstantRepository.CSV_VALUE_SEPARATOR;

public class CsvReaderImpl implements CsvReader {
    @Override
    public List<List<String>> read(String filePath) {
        List<List<String>> data = new ArrayList<>();
        File csvFile = new File(filePath);
        if (csvFile.isFile()) {
            try (BufferedReader csvReader = new BufferedReader(new FileReader(csvFile))) {
                String line;
                while ((line = csvReader.readLine()) != null) {
                    data.add(Arrays
                            .stream(line.split(CSV_VALUE_SEPARATOR))
                            .toList());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
                //TODO handle exception
            }
        }
        return data;
    }
}
