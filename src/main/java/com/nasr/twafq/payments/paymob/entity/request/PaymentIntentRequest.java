package com.nasr.twafq.payments.paymob.entity.request;

import lombok.Data;

@Data
public class PaymentIntentRequest {

    private int amount;
    private String currency;
    private int[] payment_methods;
    private Item[] items;
    private BillingData billing_data;
}
