package ru.clevertec.check.mapper;


import ru.clevertec.check.entity.Product;
import ru.clevertec.check.entity.dto.ProductDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static ru.clevertec.check.dao.DaoConstantRepository.*;

public class ProductMapper {
    public static Product map(List<String> values) {
        Product product = new Product();
        product.setId(Long.parseLong(values.get(0)));
        product.setDescription(values.get(1));
        product.setPrice(Float.parseFloat(values.get(2)));
        product.setWholesaleProduct(Boolean.parseBoolean(values.get(4)));
        return product;
    }

    public static Product map(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(product.getId());
        product.setDescription(productDTO.getDescription());
        product.setPrice(product.getPrice());
        product.setWholesaleProduct(product.isWholesaleProduct());
        return product;
    }

    public static ProductDTO map(ResultSet resultSet) throws SQLException {
        ProductDTO productDTO = new ProductDTO();
        if (resultSet.next()) {
            productDTO.setId(resultSet.getLong(PRODUCT_ID));
            productDTO.setDescription(resultSet.getString(PRODUCT_DESCRIPTION));
            productDTO.setPrice(resultSet.getFloat(PRODUCT_PRICE));
            productDTO.setQuantityInStock(resultSet.getInt(PRODUCT_QUANTITY_IN_STOCK));
            productDTO.setWholesaleProduct(resultSet.getBoolean(PRODUCT_WHOLESALE_PRODUCT));
        }
        return productDTO;
    }
}
