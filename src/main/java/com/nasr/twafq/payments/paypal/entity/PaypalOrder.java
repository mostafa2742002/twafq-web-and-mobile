package com.nasr.twafq.payments.paypal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaypalOrder {

    private double price;
    private String currency;
    private String method;
    private String intent;
    private String description;

    // example (amount, "USD", "paypal", "sale", "Buy Products")
    public PaypalOrder(Integer amount, String currency, String method, String intent, String description) {
        this.price = amount;
        this.currency = currency;
        this.method = method;
        this.intent = intent;
        this.description = description;

    }
}