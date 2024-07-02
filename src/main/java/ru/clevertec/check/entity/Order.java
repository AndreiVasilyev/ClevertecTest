package main.java.ru.clevertec.check.entity;

import java.util.HashMap;
import java.util.Objects;

public class Order {
    private HashMap<Long, Integer> products;
    int discountCardNumber;
    double balanceDebitCard;

    public Order() {
    }

    public Order(HashMap<Long, Integer> products, int discountCardNumber, double balanceDebitCard) {
        this.products = products;
        this.discountCardNumber = discountCardNumber;
        this.balanceDebitCard = balanceDebitCard;
    }

    public HashMap<Long, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Long, Integer> products) {
        this.products = products;
    }

    public int getDiscountCardNumber() {
        return discountCardNumber;
    }

    public void setDiscountCardNumber(int discountCardNumber) {
        this.discountCardNumber = discountCardNumber;
    }

    public double getBalanceDebitCard() {
        return balanceDebitCard;
    }

    public void setBalanceDebitCard(double balanceDebitCard) {
        this.balanceDebitCard = balanceDebitCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return discountCardNumber == order.discountCardNumber && Double.compare(balanceDebitCard, order.balanceDebitCard) == 0 && Objects.equals(products, order.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products, discountCardNumber, balanceDebitCard);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("products=").append(products);
        sb.append(", discountCardNumber=").append(discountCardNumber);
        sb.append(", balanceDebitCard=").append(balanceDebitCard);
        sb.append('}');
        return sb.toString();
    }
}
