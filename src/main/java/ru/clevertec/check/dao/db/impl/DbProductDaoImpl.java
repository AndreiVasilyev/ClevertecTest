package ru.clevertec.check.dao.db.impl;

import ru.clevertec.check.dao.db.DbProductDao;
import ru.clevertec.check.dao.db.PostgresConnection;
import ru.clevertec.check.entity.DbCredentials;
import ru.clevertec.check.entity.dto.ProductDTO;
import ru.clevertec.check.exception.InternalServerException;
import ru.clevertec.check.mapper.ProductMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ru.clevertec.check.dao.DaoConstantRepository.SQL_SELECT_ALL_PRODUCTS;

public class DbProductDaoImpl implements DbProductDao {

    private final Connection connection;

    public DbProductDaoImpl() {
        connection = PostgresConnection.get();
    }

    @Override
    public List<ProductDTO> getAll(DbCredentials dbCredentials) throws InternalServerException {
        try (Statement organizationStatement = connection.createStatement();
             ResultSet resultSet = organizationStatement.executeQuery(SQL_SELECT_ALL_PRODUCTS)) {
            List<ProductDTO> productDTOS = new ArrayList<>();
            while (resultSet.next()) {
                ProductDTO productDTO = ProductMapper.map(resultSet);
                productDTOS.add(productDTO);
            }
            return productDTOS;
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }
}
