package com.techelevator;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DrinkTest {


    private Drink sut;

    @Before
    public void setup(){
        sut = new Drink("B2", "Papsi", new BigDecimal("3.45"), "Drink", 5, 0 , 0);
    }

    @Test
    public void drink_test() {


        String expectedCode = "B2";
        String expectedName = "Papsi";
        BigDecimal expectedPrice = new BigDecimal("3.45");
        String expectedType = "Drink";
        int expectedQuantity = 5;


        assertEquals(expectedCode, sut.getCode());
        assertEquals(expectedName, sut.getName());
        assertEquals(expectedPrice, sut.getPrice());
        assertEquals(expectedType, sut.getType());
        assertEquals(expectedQuantity, sut.getQuantity());
    }

    @Test
    public void drink_message_test(){
        String expectedMessage = "Glug Glug, Yum!";
        assertEquals(expectedMessage, sut.getMessage());
    }

}
