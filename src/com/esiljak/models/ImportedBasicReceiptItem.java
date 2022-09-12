package com.esiljak.models;

import com.esiljak.exceptions.IllegalItemNameException;
import com.esiljak.exceptions.IllegalPriceException;
import com.esiljak.exceptions.IllegalQuantityException;
import com.esiljak.helpers.NumberHelper;

public class ImportedBasicReceiptItem extends BasicReceiptItem {
    public ImportedBasicReceiptItem(String name, float price) throws IllegalItemNameException, IllegalPriceException {
        super(name, price);
    }

    public ImportedBasicReceiptItem(String name, float price, int quantity) throws IllegalItemNameException, IllegalPriceException, IllegalQuantityException {
        super(name, price, quantity);
    }

    @Override
    public float calculateTax() {
        float extraTax = 5 * (super.getPrice() * super.getQuantity()) / 100;
        return super.calculateTax() + NumberHelper.roundUpTax(extraTax);
    }
}
