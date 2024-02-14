package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InventoryReader {

    String code = "";
    String name = "";
    BigDecimal price = new BigDecimal("0");
    String type = "";
    int quantity = 5;
    int fullPriceSold = 0;
    int discountPriceSold = 0;

    File inputFile = new File("main.csv");


    public Map<String, Items> getInitialStockedInventory() {
        Map<String, Items> inventory = new HashMap<>();
        try {
            Scanner fileScanner = new Scanner(inputFile);

            while (fileScanner.hasNextLine()) {

                    String line = fileScanner.nextLine();
                    String[] lineArr = line.split("\\,");

                if (lineArr.length == 4 && lineArr[2].matches("\\d+\\.[0-9][0-9]")) {
                    code = lineArr[0];
                    name = lineArr[1];
                    price = new BigDecimal(lineArr[2]);
                    type = lineArr[3];

                    Items item = null;

                    if (type.equalsIgnoreCase("candy")) {
                        item = new Candy(code, name, price, type, quantity, fullPriceSold, discountPriceSold);
                    } else if (type.equalsIgnoreCase("drink")) {
                        item = new Drink(code, name, price, type, quantity, fullPriceSold, discountPriceSold);
                    } else if (type.equalsIgnoreCase("gum")) {
                        item = new Gum(code, name, price, type, quantity, fullPriceSold, discountPriceSold);
                    } else if (type.equalsIgnoreCase("munchy")) {
                        item = new Munchy(code, name, price, type, quantity, fullPriceSold, discountPriceSold);
                    }

                    if (item != null) {
                        inventory.put(code, item);
                    }
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
        }
        return inventory;
    }
}
