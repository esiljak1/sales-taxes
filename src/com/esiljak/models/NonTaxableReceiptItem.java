package com.esiljak.models;

import com.esiljak.exceptions.IllegalItemNameException;
import com.esiljak.exceptions.IllegalPriceException;
import com.esiljak.exceptions.IllegalQuantityException;

public class NonTaxableReceiptItem extends ReceiptItem {

    public NonTaxableReceiptItem(String name, float price) throws IllegalItemNameException, IllegalPriceException {
        super(name, price);
    }

    public NonTaxableReceiptItem(String name, float price, int quantity) throws IllegalItemNameException, IllegalPriceException, IllegalQuantityException {
        super(name, price, quantity);
    }

    @Override
    public float calculateTax() {
        return 0;
    }
}
