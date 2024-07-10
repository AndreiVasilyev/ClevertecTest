package ru.clevertec.check.service.impl;

import ru.clevertec.check.dao.DaoProvider;
import ru.clevertec.check.dao.db.DbProductDao;
import ru.clevertec.check.entity.DbCredentials;
import ru.clevertec.check.entity.Product;
import ru.clevertec.check.entity.Stock;
import ru.clevertec.check.entity.dto.ProductDTO;
import ru.clevertec.check.exception.BadRequestException;
import ru.clevertec.check.exception.InternalServerException;
import ru.clevertec.check.mapper.DbCredentialsMapper;
import ru.clevertec.check.mapper.ProductMapper;
import ru.clevertec.check.service.DbProductService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DbProductServiceImpl implements DbProductService {
    @Override
    public Stock getStock(String[] args) throws BadRequestException, InternalServerException {
        DbCredentials dbCredentials = DbCredentialsMapper.map(args);
        DbProductDao dbProductDao = DaoProvider.getInstance().getDbProductDao();
        List<ProductDTO> productsDTO = dbProductDao.getAll(dbCredentials);
        Map<Product, Integer> products = productsDTO.stream()
                .collect(Collectors.toMap(ProductMapper::map, ProductDTO::getQuantityInStock));
        return new Stock(products);
    }
}
