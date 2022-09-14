package com.esiljak;

import com.esiljak.helpers.MainHelper;

public class Program {

    public static void main(String[] args){
        try {
            MainHelper.itemInputFlow();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
