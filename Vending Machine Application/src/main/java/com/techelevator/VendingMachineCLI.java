package com.techelevator;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.nio.file.Files.createFile;

/*
 * This class is provided to you as a *suggested* class to start
 * your project. Feel free to refactor this code as you see fit.
 */
public class VendingMachineCLI {

	public static void main(String[] args) {
		VendingMachineCLI cli = new VendingMachineCLI();
		cli.run();
	}

	private Logger logger = new Logger();

	public void run() {

		boolean stay = true;
		InventoryReader inventoryReader = new InventoryReader();
		Map<String, Items> inventory = inventoryReader.getInitialStockedInventory();
		Scanner mainMenuScanner = new Scanner(System.in);

		while(stay) {
			System.out.println("Main Menu");
			System.out.println("(1) Display Vending Machine Items");
			System.out.println("(2) Purchase");
			System.out.println("(3) Exit");
			String selection = mainMenuScanner.nextLine();

			if (selection.equals("1")) {
				displayMenu(inventory);

			} else if (selection.equals("2")) {
				purchaseMenu(inventory);

			} else if (selection.equals("3")) {
				stay = false;

			} else if (selection.equals("4")) {
				runSalesReport(inventory);

			} else {
				System.out.println("Invalid input");
				System.out.println();

			}

		}
		System.out.println();
		System.out.println("Bye!");
	}

	//Menu Display
	public void displayMenu(Map<String, Items> inventory){
		System.out.println();
		System.out.println("*** MENU ***");
		System.out.println();

		Set<String> itemCodeList = inventory.keySet();

		for (String keys : itemCodeList) {
			System.out.println(inventory.get(keys).getCode() + ") " + inventory.get(keys).getName() + " $" + inventory.get(keys).getPrice() + " Quantity Remaining: " + inventory.get(keys).getQuantity());
		}
		System.out.println();
	}

	//Selecting Items
	public void purchaseMenu(Map<String, Items> inventory){
		Scanner purchaseScanner = new Scanner(System.in);
		boolean stay = true;
		BigDecimal currentMoney = new BigDecimal("0.00");
		boolean discount = false;


		while (stay) {
			System.out.println();
			System.out.println("Current Money Provided: $" + currentMoney);
			System.out.println();
			System.out.println("Purchase Menu");
			System.out.println("(1) Feed Money");
			System.out.println("(2) Select Product");
			System.out.println("(3) Finish Transaction");
			String selection = purchaseScanner.nextLine();

			if (selection.equals("1")){
				currentMoney = feedMoney(currentMoney);

			} else if (selection.equals("2")) {
				BigDecimal beforeMoney = currentMoney;
				currentMoney = selectProduct(inventory, currentMoney, discount);

				if (currentMoney.compareTo(beforeMoney) < 0) {
					discount = !discount;
				}

			} else if (selection.equals("3")) {
				logger.writer("GIVE CHANGE:" , currentMoney , new BigDecimal("0.00"));
				String change = returnChange(currentMoney);
				System.out.println();
				System.out.println("Money Remaining: $" + currentMoney);
				System.out.println(change);
				System.out.println();
				stay = false;

			} else {
				System.out.println("Invalid input");
				System.out.println();
			}
		}
	}

	public BigDecimal feedMoney(BigDecimal currentMoney) {
		BigDecimal moneyAdded = new BigDecimal("0.00");
		Scanner moneyScanner = new Scanner(System.in);

		boolean stay = true;

		while (stay) {
			System.out.println();
			System.out.println("Please feed in whole dollar amounts (enter 0 to return to purchase menu)");
			try {
				moneyAdded = new BigDecimal(moneyScanner.nextLine()).setScale(2, RoundingMode.HALF_UP);

				if (moneyAdded.compareTo(new BigDecimal("0.00")) == 0) {
					stay = false;

				} else if (moneyAdded.compareTo(new BigDecimal("0.00")) > 0) {
					currentMoney = currentMoney.add(moneyAdded);
					logger.writer("FEED MONEY:", moneyAdded, currentMoney);

				}
			} catch (NumberFormatException e) {
				System.out.println("Not a valid input");
			}
		}
		return currentMoney;
	}

	public BigDecimal selectProduct(Map<String, Items> inventory, BigDecimal currentMoney, boolean discount) {
		Set<String> productCode = inventory.keySet();
		displayMenu(inventory);
		Scanner productScanner = new Scanner(System.in);
		System.out.println("Please enter a product code");
		String userInput = productScanner.nextLine();
		userInput = userInput.toUpperCase();

		if (!productCode.contains(userInput)){
			System.out.println("Product code doesn't exist");
			System.out.println();

		} else if (inventory.get(userInput).getQuantity() == 0) {
			System.out.println("Item Sold Out");
			System.out.println();

		} else if (currentMoney.compareTo(inventory.get(userInput).getPrice()) < 0 && !discount) {
			System.out.println("Not enough money");
			System.out.println();

		} else if (currentMoney.compareTo(inventory.get(userInput).getPrice().subtract(new BigDecimal("1"))) < 0 && discount) {
			System.out.println("Not enough money");
			System.out.println();

		} else if (currentMoney.compareTo(inventory.get(userInput).getPrice()) >= 0 && !discount) {
			currentMoney = currentMoney.subtract(inventory.get(userInput).getPrice());
			inventory.get(userInput).setQuantity(inventory.get(userInput).getQuantity() - 1);
			inventory.get(userInput).setFullPriceSold(inventory.get(userInput).getFullPriceSold() + 1);
			logger.writer(inventory.get(userInput).getName() + " " + userInput, inventory.get(userInput).getPrice(), currentMoney);
			System.out.println(inventory.get(userInput).getName() + " | Cost: $" + inventory.get(userInput).getPrice() + " | Money Remaining: $" + currentMoney);
			System.out.println(inventory.get(userInput).getMessage());

		} else if (currentMoney.compareTo(inventory.get(userInput).getPrice().subtract(new BigDecimal("1"))) >= 0 && discount) {
			currentMoney = currentMoney.subtract(inventory.get(userInput).getPrice().subtract(new BigDecimal("1")));
			inventory.get(userInput).setQuantity(inventory.get(userInput).getQuantity() - 1);
			inventory.get(userInput).setDiscountPriceSold(inventory.get(userInput).getDiscountPriceSold() + 1);
			logger.writer(inventory.get(userInput).getName() + " " + userInput, inventory.get(userInput).getPrice(), currentMoney);
			System.out.println(inventory.get(userInput).getName() + " | Cost: $" + inventory.get(userInput).getPrice() + " - $1.00 Discount | Money Remaining: $" + currentMoney);
			System.out.println(inventory.get(userInput).getMessage());
		}
		return currentMoney;
	}

	public String returnChange(BigDecimal currentMoney) {
		int changeTimes100 = currentMoney.multiply(new BigDecimal("100")).intValue();
		int quarters = changeTimes100 / 25;
		changeTimes100 = changeTimes100 % 25;
		int dimes = changeTimes100 / 10;
		changeTimes100 = changeTimes100 % 10;
		int nickels = changeTimes100 / 5;
		return "Change Returned: " + quarters + " Quarters, " + dimes + " Dimes, " + nickels + " Nickels.";
	}

	public void runSalesReport(Map<String, Items> inventory) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd--HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();

		File salesReport = new File("salesreport-" + dtf.format(now) + ".txt");
		PrintWriter writer = null;
		Set<String> itemCodeList = inventory.keySet();
		BigDecimal totalSales = new BigDecimal("0.00");

		if(salesReport.exists()){
			try {
				writer = new PrintWriter( new FileOutputStream(salesReport,true) );
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		} else {
			try {
				writer = new PrintWriter(salesReport);
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		}

		for (String keys : itemCodeList) {
			writer.append(inventory.get(keys).getName() + "|" + inventory.get(keys).getFullPriceSold() + "|" + inventory.get(keys).getDiscountPriceSold() + "\n");
		}

		for (String keys : itemCodeList) {
			totalSales = totalSales.add(inventory.get(keys).getPrice().multiply(new BigDecimal(inventory.get(keys).getFullPriceSold())));
			totalSales = totalSales.add(inventory.get(keys).getPrice().subtract(new BigDecimal("1.00")).multiply(new BigDecimal(inventory.get(keys).getDiscountPriceSold())));
			}

		writer.append("\n" + "**TOTAL SALES** $" + totalSales);
		writer.flush();
		writer.close();
		}
	}
