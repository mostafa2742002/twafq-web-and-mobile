package com.nasr.twafq.payments.braintree.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import com.nasr.twafq.payments.braintree.dto.CheckoutDTO;

import com.nasr.twafq.user.entity.User;
import com.nasr.twafq.user.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.TransactionRequest;

@RestController
public class paymentController {
    @Autowired
    private BraintreeGateway gateway;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/client_token")
    public String getClientToken(@RequestBody String userId) {
        User user = userRepository.findById(userId).orElse(null);
        
        if(user == null)
            throw new IllegalArgumentException("The User Not Found");
        if(user.getIsVerifiedUser())
            throw new IllegalArgumentException("The User Already Verified");

        

        return gateway.clientToken().generate();
    }

    @PostMapping("/checkout")
    public String processPayment(@RequestBody CheckoutDTO checkoutDTO) {
        String nonce = checkoutDTO.getNonce();
        String userId = checkoutDTO.getUserId();
        String type = checkoutDTO.getPaymentType();

        User user = userRepository.findById(userId).orElse(null);
        
        if(user == null)
            throw new IllegalArgumentException("The User Not Found");
        if(user.getIsVerifiedUser())
            throw new IllegalArgumentException("The User Already Verified");
        if(type.equals("addUser") && checkoutDTO.getTargetId() == null)
            throw new IllegalArgumentException("The Target Id Not Found");

        BigDecimal amount = new BigDecimal(checkoutDTO.getAmount()); // يمكنك تعديل المبلغ أو تمريره كمعلمة
        TransactionRequest request = new TransactionRequest()
            .amount(amount)
            .paymentMethodNonce(nonce)
            .options()
                .submitForSettlement(true)
                .done();

        Result<Transaction> result = gateway.transaction().sale(request);
        if (result.isSuccess()) {
            if(type.equals("verify"))
            {
                user.setIsVerifiedUser(true);
            }
            else if (type.equals("addUser"))
            {
                user.getUsersContactWith().add(checkoutDTO.getTargetId());
            }

            userRepository.save(user);

            return "Transaction successful" ;
            // + result.getTarget().getId();
        } else {
            return "Transaction failed: " + result.getMessage();
        }
    }

}
