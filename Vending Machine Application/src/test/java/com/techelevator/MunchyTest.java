package com.techelevator;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class MunchyTest {

    private Munchy sut;

    @Before
    public void setup(){
        sut = new Munchy("A4", "Chippos", new BigDecimal("3.85"), "Munchy", 5, 0, 0);
    }

    @Test
    public void munchy_test() {


        String expectedCode = "A4";
        String expectedName = "Chippos";
        BigDecimal expectedPrice = new BigDecimal("3.85");
        String expectedType = "Munchy";
        int expectedQuantity = 5;


        assertEquals(expectedCode, sut.getCode());
        assertEquals(expectedName, sut.getName());
        assertEquals(expectedPrice, sut.getPrice());
        assertEquals(expectedType, sut.getType());
        assertEquals(expectedQuantity, sut.getQuantity());
    }

    @Test
    public void munchy_message_test(){
        String expectedMessage = "Crunch Crunch, Yum!";
        assertEquals(expectedMessage, sut.getMessage());
    }

}