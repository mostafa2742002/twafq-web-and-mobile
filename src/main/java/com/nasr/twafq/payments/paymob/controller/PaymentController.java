package com.nasr.twafq.payments.paymob.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nasr.twafq.dto.ResponseDto;
import com.nasr.twafq.payments.paymob.entity.callback.TransactionCallback;
import com.nasr.twafq.payments.paymob.service.PaymentService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;


    @GetMapping("/create-verify-intent")
    public ResponseEntity<ResponseDto> createVerifyIntent(@RequestParam int amount,
            @RequestParam("user_id") String userId) {
        return paymentService.createVerifyIntent(amount, userId);
    }

    @GetMapping("/create-add-user-intent")
    public ResponseEntity<ResponseDto> createAddUserIntent(@RequestParam int amount,
            @RequestParam("user_id") String userId, @RequestParam("target_id") String targetId) {
        return paymentService.createAddUserIntent(amount, userId, targetId);
    }

    @PostMapping("/callback/verify")
    public void verifyCallback(@RequestBody TransactionCallback transactionCallback) {
        paymentService.handleVerifyCallback(transactionCallback);
    }

    @PostMapping("/callback/add-user")
    public void addUserCallback(@RequestBody TransactionCallback transactionCallback) {
        paymentService.handleAddUserCallback(transactionCallback);
    }
}
