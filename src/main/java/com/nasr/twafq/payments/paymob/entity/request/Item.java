package com.nasr.twafq.payments.paymob.entity.request;

import lombok.Data;

@Data
public class Item {
    private String name;
    private int amount;
    private int quantity;

    public Item(String name, int amount, int quantity) {
        this.name = name;
        this.amount = amount;
        this.quantity = quantity;
    }
}
