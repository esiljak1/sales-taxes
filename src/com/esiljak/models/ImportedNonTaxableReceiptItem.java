package com.esiljak.models;

import com.esiljak.exceptions.IllegalItemNameException;
import com.esiljak.exceptions.IllegalPriceException;
import com.esiljak.exceptions.IllegalQuantityException;
import com.esiljak.helpers.NumberHelper;

public class ImportedNonTaxableReceiptItem extends NonTaxableReceiptItem {
    public ImportedNonTaxableReceiptItem(String name, float price) throws IllegalItemNameException, IllegalPriceException {
        super(name, price);
    }

    public ImportedNonTaxableReceiptItem(String name, float price, int quantity) throws IllegalItemNameException, IllegalPriceException, IllegalQuantityException {
        super(name, price, quantity);
    }

    @Override
    public float calculateTax() {
        float tax = 5 * (super.getPrice() * super.getQuantity()) / 100;
        return super.calculateTax() + NumberHelper.roundUpTax(tax);
    }
}
