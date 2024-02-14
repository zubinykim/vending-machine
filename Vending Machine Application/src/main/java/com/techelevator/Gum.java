package com.techelevator;

import java.math.BigDecimal;

public class Gum extends Items{


    public Gum (String code, String name, BigDecimal price, String type, int quantity, int fullPriceSold, int discountPriceSold) {
        super(code, name, price, type, quantity, fullPriceSold, discountPriceSold);
    }
    public String getMessage() {
        return "Chew Chew, Yum!";
    }
}
