package com.esiljak.models;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private List<ReceiptItem> items;

    public Receipt() {
        items = new ArrayList<>();
    }

    public Receipt(List<ReceiptItem> items) {
        this.items = items;
    }

    public final List<ReceiptItem> getItems() {
        return items;
    }

    public void setItems(List<ReceiptItem> items) {
        this.items = items;
    }

    public void addItem(ReceiptItem item){
        items.add(item);
    }

    public float calculateTax(){
        return ((float) items.stream().mapToDouble(ReceiptItem::calculateTax).sum());
    }

    public float calculateTotalPrice(){
        return ((float) items.stream().mapToDouble(ReceiptItem::getPrice).sum()) + calculateTax();
    }
}
