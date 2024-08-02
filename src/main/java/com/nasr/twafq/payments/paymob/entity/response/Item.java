package com.nasr.twafq.payments.paymob.entity.response;

import lombok.Data;

@Data
public class Item {

    private String name;
    private int amount;
    private String description;
    private int quantity;

}
