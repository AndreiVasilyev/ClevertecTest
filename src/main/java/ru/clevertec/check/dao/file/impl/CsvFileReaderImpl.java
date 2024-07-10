package ru.clevertec.check.dao.file.impl;

import ru.clevertec.check.dao.file.CsvFileReader;
import ru.clevertec.check.exception.InternalServerException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.clevertec.check.dao.DaoConstantRepository.CSV_VALUE_SEPARATOR;


public class CsvFileReaderImpl implements CsvFileReader {
    @Override
    public List<List<String>> read(String filePath) throws InternalServerException {
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
                throw new InternalServerException();
            }
        } else {
            throw new InternalServerException();
        }
        return data;
    }
}
