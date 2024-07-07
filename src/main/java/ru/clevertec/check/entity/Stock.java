package main.java.ru.clevertec.check.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Stock {
    private Map<Product, Integer> products;

    public Stock() {
    }

    public Stock(Map<Product, Integer> products) {
        this.products = products;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Product, Integer> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(products, stock.products);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(products);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "products=" + products +
                '}';
    }
}
