package com.esiljak.models;

import com.esiljak.exceptions.IllegalItemNameException;
import com.esiljak.exceptions.IllegalPriceException;
import com.esiljak.exceptions.IllegalQuantityException;
import com.esiljak.helpers.NumberHelper;

public class BasicReceiptItem extends ReceiptItem {

    public BasicReceiptItem(String name, float price) throws IllegalItemNameException, IllegalPriceException {
        super(name, price);
    }

    public BasicReceiptItem(String name, float price, int quantity) throws IllegalItemNameException, IllegalPriceException, IllegalQuantityException {
        super(name, price, quantity);
    }

    @Override
    public float calculateTax() {
        return NumberHelper.roundUpTax((super.getPrice() * super.getQuantity()) / 10);
    }
}
