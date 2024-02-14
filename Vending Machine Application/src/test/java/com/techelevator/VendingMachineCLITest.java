package com.techelevator;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class VendingMachineCLITest {

    private VendingMachineCLI sut;

    @Before
    public void setup() {
        sut = new VendingMachineCLI();

    }


        @Test
    public void change_400_test(){
        BigDecimal currentMoney = new BigDecimal("4");

        assertEquals("Change Returned: 16 Quarters, 0 Dimes, 0 Nickels.", sut.returnChange(currentMoney));

    }

    @Test
    public void change_090_test(){
        BigDecimal currentMoney = new BigDecimal("0.90");

        assertEquals("Change Returned: 3 Quarters, 1 Dimes, 1 Nickels.", sut.returnChange(currentMoney));

    }

}