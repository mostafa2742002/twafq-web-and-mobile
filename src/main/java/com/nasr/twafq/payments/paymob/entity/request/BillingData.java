package com.nasr.twafq.payments.paymob.entity.request;

import lombok.Data;

@Data
public class BillingData {

    private String first_name;
    private String last_name;
    private String phone_number;
    private String country;
    private String email;

    public BillingData(String firstName, String lastName, String phoneNumber, String country, String email) {
        this.first_name = firstName;
        this.last_name = lastName;
        this.phone_number = phoneNumber;
        this.country = country;
        this.email = email;
    }
}
