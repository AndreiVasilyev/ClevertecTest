package ru.clevertec.check.entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


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

    public HashMap<Product, Double> getTotals() {
        return (HashMap<Product, Double>) products.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, v -> (double) (v.getKey().getPrice()) * v.getValue()));
    }

    public HashMap<Product, Double> getDiscounts() {
        return (HashMap<Product, Double>) products.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, pair -> {
                    double discount = 0.0;
                    if (pair.getKey().isWholesaleProduct() && pair.getValue() > 4) {
                        discount = 0.1;
                    } else if (discountCard != null) {
                        discount = discountCard.getDiscountAmount() / 100.00;
                    }
                    return pair.getKey().getPrice() * pair.getValue() * discount;
                }));
    }


    public Double getTotalPrice() {
        return getTotals()
                .values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public Double getTotalDiscount() {
        return getDiscounts()
                .values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum();
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
        return "Check{" +
                "dateTime=" + dateTime +
                ", products=" + products +
                ", discountCard=" + discountCard +
                '}';
    }
}
