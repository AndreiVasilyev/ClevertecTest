package ru.clevertec.check.dao.file.impl;

import ru.clevertec.check.dao.file.CsvFileWriter;
import ru.clevertec.check.exception.InternalServerException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvFileWriterImpl implements CsvFileWriter {
    @Override
    public void write(List<String> lines, String pathToFile) throws InternalServerException {
        try (FileWriter fileWriter = new FileWriter(pathToFile)) {
            for (String line : lines) {
                fileWriter.append(line);
            }
            fileWriter.flush();
        } catch (IOException e) {
            throw new InternalServerException(e);
        }
    }
}
