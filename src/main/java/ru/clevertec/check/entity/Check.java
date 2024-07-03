package main.java.ru.clevertec.check.entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Check {
    LocalDateTime dateTime;
    HashMap<Product, Integer> products;
    DiscountCard discountCard;

    public Check() {
        dateTime = LocalDateTime.now();
        products = new HashMap<>();
    }

    public Check(LocalDateTime dateTime, HashMap<Product, Integer> products, DiscountCard discountCard) {
        this.dateTime = dateTime;
        this.products = products;
        this.discountCard = discountCard;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Product, Integer> products) {
        this.products = products;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public void setDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
    }

    public Double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Map.Entry<Product, Integer> product : products.entrySet()) {
            totalPrice += product.getKey().getPrice() * product.getValue();
        }
        return totalPrice;
    }

    public Double calculateTotalDiscount() {
        double totalDiscount = 0.0;
        for (Map.Entry<Product, Integer> product : products.entrySet()) {
            if (product.getKey().isWholesaleProduct()) {
                totalDiscount += product.getKey().getPrice() * product.getValue() * 0.1;
            } else if (discountCard != null) {
                totalDiscount += product.getKey().getPrice() * product.getValue() * discountCard.getDiscountAmount() / 100;
            }
        }
        return totalDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Check check = (Check) o;
        return Objects.equals(dateTime, check.dateTime) && Objects.equals(products, check.products) && Objects.equals(discountCard, check.discountCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, products, discountCard);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Check{");
        sb.append("dateTime=").append(dateTime);
        sb.append(", products=").append(products);
        sb.append(", discountCard=").append(discountCard);
        sb.append('}');
        return sb.toString();
    }
}
