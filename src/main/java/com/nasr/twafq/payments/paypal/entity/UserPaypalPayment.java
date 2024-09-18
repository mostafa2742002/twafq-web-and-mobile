package com.nasr.twafq.payments.paypal.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "userPayment")
public class UserPaypalPayment {

    private String userId;
    private String paymentId;
    private String targetId;
}
