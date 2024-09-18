package com.nasr.twafq.payments.paypal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nasr.twafq.payments.paypal.service.PaymentPaypalService;
import com.paypal.base.rest.PayPalRESTException;

@RestController
@RequestMapping("/payment")
public class PaymentPaypalController {

    @Autowired
    private PaymentPaypalService paymentPaypalService;

    @PostMapping("/create-verify-intent")
    public ResponseEntity<String> createVerifyIntent(@RequestParam String userId, @RequestParam Integer amount)
            throws PayPalRESTException {
        return paymentPaypalService.createVerifyIntent(userId, amount);
    }

    @PostMapping("/create-add-user-intent")
    public ResponseEntity<String> createAddUserIntent(@RequestParam String userId, @RequestParam Integer amount,
            @RequestParam String targetId) throws PayPalRESTException {
        return paymentPaypalService.createAddUserIntent(userId, amount, targetId);
    }

    @PostMapping("/success/verify")
    public ResponseEntity<String> handleVerifySuccess(@RequestParam String paymentId, @RequestParam String payerId)
            throws PayPalRESTException {
        return paymentPaypalService.handleVerifySuccess(paymentId, payerId);
    }

    @PostMapping("/success/add-user")
    public ResponseEntity<String> handleAddUserSuccess(@RequestParam String paymentId, @RequestParam String payerId)
            throws PayPalRESTException {
        return paymentPaypalService.handleAddUserSuccess(paymentId, payerId);
    }
}
