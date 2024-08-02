package com.nasr.twafq.payments.paymob.entity.response;

import lombok.Data;

@Data
public class PaymentKey {

    private int integration;
    private String key;
    private String gateway_type;
    private Object iframe_id;
}
