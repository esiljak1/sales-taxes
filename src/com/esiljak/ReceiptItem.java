package com.esiljak;

public class ReceiptItem {
    private String itemName;
    private float price;
    private int quantity = 1;

    public ReceiptItem(String itemName, float price) {
        this.itemName = itemName;
        this.price = price;
    }

    public ReceiptItem(String itemName, float price, int quantity) {
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
