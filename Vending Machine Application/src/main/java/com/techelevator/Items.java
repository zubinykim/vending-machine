package com.techelevator;

import java.math.BigDecimal;

public abstract class Items {

    private String code;
    private String name;
    private BigDecimal price;
    private String type;
    private int quantity;
    private int fullPriceSold;
    private int discountPriceSold;

    public Items(String code, String name, BigDecimal price, String type, int quantity, int fullPriceSold, int discountPriceSold) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
        this.fullPriceSold = fullPriceSold;
        this.discountPriceSold = discountPriceSold;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getFullPriceSold() {
        return fullPriceSold;
    }

    public int getDiscountPriceSold() {
        return discountPriceSold;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setFullPriceSold(int fullPriceSold) {
        this.fullPriceSold = fullPriceSold;
    }

    public void setDiscountPriceSold(int discountPriceSold) {
        this.discountPriceSold = discountPriceSold;
    }

   public abstract String getMessage();
}
