package com.techelevator;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class GumTest {

    private Gum sut;

    @Before
    public void setup(){
        sut = new Gum("A1", "U-Chews", new BigDecimal("1.65"), "Gum", 5, 0, 0);
    }

    @Test
    public void gum_test() {


        String expectedCode = "A1";
        String expectedName = "U-Chews";
        BigDecimal expectedPrice = new BigDecimal("1.65");
        String expectedType = "Gum";
        int expectedQuantity = 5;


        assertEquals(expectedCode, sut.getCode());
        assertEquals(expectedName, sut.getName());
        assertEquals(expectedPrice, sut.getPrice());
        assertEquals(expectedType, sut.getType());
        assertEquals(expectedQuantity, sut.getQuantity());
    }

    @Test
    public void gum_message_test(){
        String expectedMessage = "Chew Chew, Yum!";
        assertEquals(expectedMessage, sut.getMessage());
    }

}