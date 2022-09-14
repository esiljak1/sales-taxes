package com.esiljak.models;

import com.esiljak.helpers.NumberHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Receipt {
    private List<ReceiptItem> items;

    public Receipt() {
        items = new ArrayList<>();
    }

    public Receipt(List<ReceiptItem> items) {
        this.items = items;
    }

    public List<ReceiptItem> getItems() {
        //Unmodifiable list to prohibit user from adding items through getter
        return Collections.unmodifiableList(items);
    }

    public void setItems(List<ReceiptItem> items) {
        this.items = items;
    }

    public void addItem(ReceiptItem item){
        items.add(item);
    }

    public float calculateTax(){
        return NumberHelper.roundUpTax((float) items.stream().mapToDouble(ReceiptItem::calculateTax).sum());
    }

    public float calculateTotalPrice(){
        return ((float) items.stream().mapToDouble(ReceiptItem::getPriceWithTax).sum());
    }
}
