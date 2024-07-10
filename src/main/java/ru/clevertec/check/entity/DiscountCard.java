package ru.clevertec.check.entity;

import java.util.Objects;

public class DiscountCard {
    private long id;
    private int number;
    private int discountAmount;

    public DiscountCard() {
    }

    public DiscountCard(long id, int number, int discountAmount) {
        this.id = id;
        this.number = number;
        this.discountAmount = discountAmount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountCard that = (DiscountCard) o;
        return id == that.id && number == that.number && discountAmount == that.discountAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, discountAmount);
    }

    @Override
    public String toString() {
        return "DiscountCard{" + "id=" + id +
                ", number=" + number +
                ", discountAmount=" + discountAmount +
                '}';
    }
}
