package com.esiljak.models;

import com.esiljak.exceptions.IllegalItemNameException;
import com.esiljak.exceptions.IllegalPriceException;
import com.esiljak.exceptions.IllegalQuantityException;
import com.esiljak.helpers.NumberHelper;

public class BasicReceiptItem {
    private String name;
    private float price;
    private int quantity = 1;

    private void checkItemName(String itemName) throws IllegalItemNameException {
        if(itemName == null || itemName.trim().isEmpty())
            throw new IllegalItemNameException("Item name cannot be null or empty");
    }

    private void checkPrice(float price) throws IllegalPriceException {
        if(price < 0)
            throw new IllegalPriceException("Price cannot be negative");
    }

    private void checkQuantity(int quantity) throws IllegalQuantityException {
        if(quantity <= 0)
            throw new IllegalQuantityException("Quantity must be positive");
    }

    public BasicReceiptItem(String name, float price) throws IllegalItemNameException, IllegalPriceException {
        checkItemName(name);
        checkPrice(price);
        this.name = name;
        this.price = price;
    }

    public BasicReceiptItem(String name, float price, int quantity) throws IllegalItemNameException, IllegalPriceException, IllegalQuantityException {
        this(name, price);
        checkQuantity(quantity);
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalItemNameException {
        checkItemName(name);
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) throws IllegalPriceException {
        checkPrice(price);
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws IllegalQuantityException {
        checkQuantity(quantity);
        this.quantity = quantity;
    }

    public float calculateTax() {
        return NumberHelper.roundUpTax(10 * (price * quantity) / 100);
    }
}