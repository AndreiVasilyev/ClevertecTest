package ru.clevertec.check.mapper;

import ru.clevertec.check.entity.DbCredentials;
import ru.clevertec.check.exception.BadRequestException;

import java.util.Arrays;
import java.util.List;

import static ru.clevertec.check.mapper.MapperConstantRepository.*;

public class DbCredentialsMapper {

    public static DbCredentials map(String[] args) throws BadRequestException {
        DbCredentials credentials = new DbCredentials();
        List<String> datasourceArgs = Arrays.stream(args)
                .filter(arg -> arg.contains(DATASOURCE_PARAM_PREFIX))
                .toList();
        for (String arg : datasourceArgs) {
            String[] parts = arg.split(PARAMETERS_SEPARATOR);
            if (parts.length == 2) {
                String prefix = parts[0].trim();
                if (prefix.equals(DATASOURCE_URL_PARAM_PREFIX)) {
                    credentials.setUrl(parts[1].trim());
                } else if (prefix.equals(DATASOURCE_USERNAME_PARAM_PREFIX)) {
                    credentials.setUsername(parts[1].trim());
                } else {
                    credentials.setPassword(parts[1].trim());
                }
            } else throw new BadRequestException();
        }
        return credentials;
    }
}
