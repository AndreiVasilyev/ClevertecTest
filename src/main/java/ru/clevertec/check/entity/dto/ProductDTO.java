package ru.clevertec.check.entity.dto;

import java.util.Objects;

public class ProductDTO {
    private long id;
    private String description;
    private float price;
    private boolean isWholesaleProduct;
    private int quantityInStock;

    public ProductDTO() {
    }

    public ProductDTO(long id, String description, float price, boolean isWholesaleProduct, int quantityInStock) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.isWholesaleProduct = isWholesaleProduct;
        this.quantityInStock = quantityInStock;
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

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return id == that.id && Float.compare(price, that.price) == 0 && isWholesaleProduct == that.isWholesaleProduct && quantityInStock == that.quantityInStock && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, price, isWholesaleProduct, quantityInStock);
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", isWholesaleProduct=" + isWholesaleProduct +
                ", quantityInStock=" + quantityInStock +
                '}';
    }
}
