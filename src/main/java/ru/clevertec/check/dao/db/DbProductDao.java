package ru.clevertec.check.dao.db;

import ru.clevertec.check.entity.DbCredentials;
import ru.clevertec.check.entity.dto.ProductDTO;
import ru.clevertec.check.exception.InternalServerException;

import java.util.List;

public interface DbProductDao {
    List<ProductDTO> getAll(DbCredentials dbCredentials) throws InternalServerException;
}
