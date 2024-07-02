package main.java.ru.clevertec.check.mapper;

import main.java.ru.clevertec.check.entity.Product;

import java.util.List;

public class ProductMapper {
    public static Product map(List<String> values) {
        Product product = new Product();
        product.setId(Long.parseLong(values.get(0)));
        product.setDescription(values.get(1));
        product.setPrice(Float.parseFloat(values.get(2)));
        product.setWholesaleProduct(Boolean.parseBoolean(values.get(4)));
        return product;
    }
}
