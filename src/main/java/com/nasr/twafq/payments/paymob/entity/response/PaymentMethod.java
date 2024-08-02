package com.nasr.twafq.payments.paymob.entity.response;

import lombok.Data;

@Data
public class PaymentMethod {

    private int integration_id;
    private String alias;
    private String name;
    private String method_type;
    private String currency;
    private boolean live;
    private boolean use_cvc_with_moto;
}
