package com.esiljak.helpers;

public class NumberHelper {
    public static float roundUpTax(float tax){
        return (Math.round(tax * 20)) / 20.f;
    }
}
