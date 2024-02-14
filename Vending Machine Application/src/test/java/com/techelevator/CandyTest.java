package com.techelevator;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;



public class CandyTest {

    private Candy sut;

    @Before
    public void setup(){
        sut = new Candy("C3", "Moonpie", new BigDecimal("2.95"), "Candy", 5, 0, 0);
    }

    @Test
    public void candy_test(){

//        String code = "C3";
//        String name = "Moonpie";
//        BigDecimal price = new BigDecimal("2.95");
//        String type = "Candy";
//        int quantity = 5;


//        Candy sut = new Candy(code, name, price, type, quantity);

        String expectedCode = "C3";
        String expectedName = "Moonpie";
        BigDecimal expectedPrice = new BigDecimal("2.95");
        String expectedType = "Candy";
        int expectedQuantity = 5;

//        Map<String, Candy> expectedMap = new HashMap<>();
//        expectedMap.put("C3", Candy);
//        expectedMap.put("C4", )
//
//        Map<String, Items> actualResult = ("C3", Candy);

        assertEquals(expectedCode, sut.getCode());
        assertEquals(expectedName, sut.getName());
        assertEquals(expectedPrice, sut.getPrice());
        assertEquals(expectedType, sut.getType());
        assertEquals(expectedQuantity, sut.getQuantity());

//
//        inventory.put("C3", );
//        BigDecimal currentMoney = new BigDecimal("3");
//        boolean discount = false;
//        sut.selectProduct(inventory, currentMoney, discount);

    }

    @Test
    public void candy_message_test(){
        String expectedMessage = "Yummy Yummy, So Sweet!";
        assertEquals(expectedMessage, sut.getMessage());
    }

}