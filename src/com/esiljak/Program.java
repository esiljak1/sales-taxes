package com.esiljak;

import com.esiljak.helpers.MainHelper;
import com.esiljak.models.Receipt;

public class Program {

    public static void main(String[] args) throws Exception{
        Receipt receipt = new Receipt();
        while(true){
            int choice = MainHelper.commandInput();
            if(choice == MainHelper.END_INPUT){
                MainHelper.printReceipt(receipt);
                break;
            }
            receipt.addItem(MainHelper.itemInputFlow());
        }
    }
}
