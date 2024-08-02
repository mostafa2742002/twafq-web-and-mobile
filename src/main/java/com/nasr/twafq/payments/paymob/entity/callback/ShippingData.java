package com.nasr.twafq.payments.paymob.entity.callback;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ShippingData {
    private long id;
    private String firstName;
    private String lastName;
    private String street;
    private String building;
    private String floor;
    private String apartment;
    private String city;
    private String state;
    private String country;
    private String email;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("postal_code")
    private String postalCode;
    @JsonProperty("extra_description")
    private String extraDescription;
    @JsonProperty("shipping_method")
    private String shippingMethod;
    @JsonProperty("order_id")
    private long orderId;
}
