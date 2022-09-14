package com.esiljak;

import com.esiljak.helpers.MainHelper;

public class Program {

    public static void main(String[] args){
        int input = MainHelper.commandInput();
        if(input == MainHelper.ITEM_INPUT){
            System.out.println("Item input");
        }else if(input == MainHelper.END_INPUT){
            System.out.println("End input");
        }else{
            System.out.println("oops");
        }
    }
}
