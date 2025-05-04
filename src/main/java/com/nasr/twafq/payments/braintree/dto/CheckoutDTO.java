package com.nasr.twafq.payments.braintree.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutDTO {

    String nonce;
    String userId;
    String paymentType;
    String targetId;
    String amount;
}
