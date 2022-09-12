package com.esiljak.model;

import com.esiljak.exceptions.IllegalItemNameException;

public class ReceiptItem {
    private String itemName;
    private float price;
    private int quantity = 1;

    private void checkItemName(String itemName) throws IllegalItemNameException {
        if(itemName == null || itemName.trim().isEmpty())
            throw new IllegalItemNameException("Item name cannot be null or empty");
    }

    public ReceiptItem(String itemName, float price) throws IllegalItemNameException {
        checkItemName(itemName);
        this.itemName = itemName;
        this.price = price;
    }

    public ReceiptItem(String itemName, float price, int quantity) throws IllegalItemNameException {
        this(itemName, price);
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
