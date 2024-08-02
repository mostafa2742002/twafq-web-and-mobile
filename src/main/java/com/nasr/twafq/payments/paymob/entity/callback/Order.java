package com.nasr.twafq.payments.paymob.entity.callback;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Order {

    private long id;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("delivery_needed")
    private boolean deliveryNeeded;
    private Merchant merchant;
    @JsonProperty("amount_cents")
    private int amountCents;
    @JsonProperty("shipping_data")
    private ShippingData shippingData;
    private String currency;
}
