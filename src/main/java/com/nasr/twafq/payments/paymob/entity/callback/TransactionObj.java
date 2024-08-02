package com.nasr.twafq.payments.paymob.entity.callback;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TransactionObj {

    private long id;
    private boolean pending;
    @JsonProperty("amount_cents")
    private int amountCents;
    private boolean success;
    @JsonProperty("created_at")
    private String createdAt;
    private Order order;
    private String currency;
}
