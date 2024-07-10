package ru.clevertec.check.mapper;

import java.util.Arrays;

import static ru.clevertec.check.mapper.MapperConstantRepository.*;


public class FilePathMapper {

    public static String mapPathToFile(String[] args) {
        return Arrays.stream(args)
                .filter(arg -> arg.contains(PATH_TO_FILE_PARAM_PREFIX))
                .map(arg -> arg.split(PARAMETERS_SEPARATOR)[1].trim())
                .findFirst()
                .orElse(EMPTY_STRING);
    }

    public static String mapSaveToFile(String[] args) {
        return Arrays.stream(args)
                .filter(arg -> arg.contains(SAVE_TO_FILE_PARAM_PREFIX))
                .map(arg -> arg.split(PARAMETERS_SEPARATOR)[1].trim())
                .findFirst()
                .orElse(EMPTY_STRING);
    }
}