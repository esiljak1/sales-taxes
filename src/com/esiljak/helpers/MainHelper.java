package com.esiljak.helpers;

import com.esiljak.models.*;

import java.util.Locale;
import java.util.Scanner;

public class MainHelper {
    private static final Scanner scanner = new Scanner(System.in);
    public static final int ITEM_INPUT = 1,
                            END_INPUT = 0;

    private static boolean ynQuestion(String message){
        while(true){
            System.out.println(message);
            System.out.print("> ");
            boolean answer;
            try {
                answer = scanner.nextBoolean();
            }catch (Exception e){
                scanner.nextLine();
                System.out.println("Wrong input, please try again.");
                continue;
            }
            return answer;
        }
    }

    private static String itemNameInput(){
        scanner.nextLine();
        while (true) {
            System.out.println("Please enter item name:");
            System.out.print("> ");
            String name;
            try {
                name = scanner.nextLine();
            }catch (Exception e){
                scanner.nextLine();
                System.out.println("Invalid input, please try again!");
                continue;
            }
            return name;
        }
    }

    private static float itemPriceInput(){
        scanner.useLocale(Locale.ENGLISH);
        while (true) {
            System.out.println("Please enter item price:");
            System.out.print("> ");
            float price;
            try {
                price = scanner.nextFloat();
            }catch (Exception e){
                scanner.nextLine();
                System.out.println("Invalid input, please try again!");
                continue;
            }
            if(price < 0){
                System.out.println("Invalid input, please try again!");
                System.out.println("Price cannot be negative");
                continue;
            }
            return price;
        }
    }

    private static int itemQuantityInput(){
        while (true) {
            System.out.println("Please enter item quantity: ");
            System.out.print("> ");
            int quantity;
            try {
                quantity = scanner.nextInt();
            }catch (Exception e){
                scanner.nextLine();
                System.out.println("Invalid input, please try again!");
                continue;
            }
            if(quantity <= 0){
                System.out.println("Invalid input, please try again!");
                System.out.println("Quantity has to be positive");
                continue;
            }
            return quantity;
        }
    }

    private static ReceiptItem getItemType(boolean isTaxable, boolean isImported) throws Exception{
        String name = itemNameInput();
        float price = itemPriceInput();
        int quantity = itemQuantityInput();
        if(isTaxable){
            if(isImported){
                return new ImportedBasicReceiptItem(name, price, quantity);
            }else{
                return new BasicReceiptItem(name, price, quantity);
            }
        }else{
            if(isImported){
                return new ImportedNonTaxableReceiptItem(name, price, quantity);
            }else{
                return new NonTaxableReceiptItem(name, price, quantity);
            }
        }
    }

    public static int commandInput(){
        int input = -1;
        while(input != 0 && input != 1) {
            System.out.println("Please choose the following command:");
            System.out.println("> 1 - Item input");
            System.out.println("> 0 - Proceed to checkout");
            System.out.print("> ");
            try{
                input = scanner.nextInt();
            }catch (Exception e){
                System.out.println("Invalid command!");
                continue;
            }
            if(input != 0 && input != 1){
                System.out.println("Invalid command!");
            }
        }
        return input;
    }

    public static ReceiptItem itemInputFlow() throws Exception {
        boolean isTaxable = ynQuestion("Is the item taxable?");
        boolean isImported = ynQuestion("Is the item imported?");

        return getItemType(isTaxable, isImported);
    }

    public static void printReceipt(Receipt receipt){
        System.out.println("\n------------------------------");
        System.out.println(receipt.toString());
        System.out.println("------------------------------");
    }
}
