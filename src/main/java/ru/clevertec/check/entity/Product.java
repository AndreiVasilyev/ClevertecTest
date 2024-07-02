package main.java.ru.clevertec.check.entity;

import java.util.Objects;

public class Product {
    private long id;
    private String description;
    private float price;
    private boolean isWholesaleProduct;

    public Product() {
    }

    public Product(long id, String description, float price, boolean isWholesaleProduct) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.isWholesaleProduct = isWholesaleProduct;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isWholesaleProduct() {
        return isWholesaleProduct;
    }

    public void setWholesaleProduct(boolean wholesaleProduct) {
        isWholesaleProduct = wholesaleProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Float.compare(price, product.price) == 0 && isWholesaleProduct == product.isWholesaleProduct && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, price, isWholesaleProduct);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", isWholesaleProduct=").append(isWholesaleProduct);
        sb.append('}');
        return sb.toString();
    }
}
