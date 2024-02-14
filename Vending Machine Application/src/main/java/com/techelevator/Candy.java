package com.techelevator;

import java.math.BigDecimal;

public class Candy extends Items{


    public Candy (String code, String name, BigDecimal price, String type, int quantity, int fullPriceSold, int discountPriceSold) {
        super(code, name, price, type, quantity, fullPriceSold, discountPriceSold);
    }
    public String getMessage() {
        return "Yummy Yummy, So Sweet!";
    }
}
