package com.esiljak.helpers;

import java.util.Scanner;

public class MainHelper {
    private static final Scanner scanner = new Scanner(System.in);
    public static final int ITEM_INPUT = 1,
                            END_INPUT = 0;

    public static int commandInput(){
        int input = -1;
        while(input != 0 && input != 1) {
            System.out.println("Please choose the following command:");
            System.out.println("> 1 - Item input");
            System.out.println("> 0 - Proceed to checkout");
            System.out.print("> ");
            input = scanner.nextInt();
            if(input != 0 && input != 1){
                System.out.println("Invalid command!");
            }
        }
        return input;
    }
}
