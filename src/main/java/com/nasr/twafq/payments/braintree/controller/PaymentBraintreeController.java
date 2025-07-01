package com.nasr.twafq.payments.braintree.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.braintreegateway.*;
import com.nasr.twafq.payments.braintree.dto.CheckoutDTO;
import com.nasr.twafq.user.entity.User;
import com.nasr.twafq.user.repo.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/payments")
public class PaymentBraintreeController {

    @Autowired
    private BraintreeGateway gateway;

    @Autowired
    private UserRepository userRepository;

    /** 
     * Renders the checkout page, injecting all “page” parameters.
     * GET /payments/checkoutPage
     *    ?userId=...
     *    &paymentType=verify|addUser
     *    [&targetId=...]
     *    [&amount=...]
     */
    @GetMapping("/checkoutPage")
    public String checkoutPage(
            @RequestParam("userId") String userId,
            @RequestParam(name="paymentType", defaultValue="verify") String paymentType,
            @RequestParam(name="targetId", required=false) String targetId,
            @RequestParam(name="amount", defaultValue="10.00") String amount,
            HttpServletRequest request,
            Model model) {

        // optional: validate user exists
        userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // ➊ pull the raw Authorization header (e.g. "Bearer eyJ…")
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Missing or invalid Authorization header");
        }

        model.addAttribute("userId", userId);
        model.addAttribute("paymentType", paymentType);
        model.addAttribute("targetId", targetId);
        model.addAttribute("amount", amount);
        model.addAttribute("jwtToken",    authHeader);
        return "checkout";  // Thymeleaf template: checkout.html
    }

    @GetMapping("/client_token")
    @ResponseBody
    public String getClientToken(@RequestParam("userId") String userId) {
        if(userRepository.findById(userId).isEmpty())
            throw new IllegalArgumentException("User not found");
        
        return gateway.clientToken().generate();
    }

    @PostMapping(value = "/checkout", consumes = "application/json")
    @ResponseBody
    public String processPayment(@RequestBody CheckoutDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                       .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if ("verify".equals(dto.getPaymentType())  && user.getIsVerifiedUser())
            return "User Already Verified";
        if ("addUser".equals(dto.getPaymentType()) && dto.getTargetId() == null)
            throw new IllegalArgumentException("Target ID required");
        if (user.getUsersContactWith().contains(dto.getTargetId()))
            return "User Already Added";

            String rawAmt = dto.getAmount();
        // remove anything that is not a digit or dot
        String cleaned = rawAmt.replaceAll("[^\\d.]", "");
        BigDecimal amount = new BigDecimal(cleaned);

        TransactionRequest request = new TransactionRequest()
            .amount(amount)
            .paymentMethodNonce(dto.getNonce())
            .options()
              .submitForSettlement(true)
              .done();

        Result<Transaction> result = gateway.transaction().sale(request);
        if (!result.isSuccess())
            return "Transaction failed: " + result.getMessage();

        if ("verify".equals(dto.getPaymentType())) {
            user.setIsVerifiedUser(true);
        } else {
            user.getUsersContactWith().add(dto.getTargetId());
        }
        userRepository.save(user);
        return "Transaction successful: " + result.getTarget().getId();
    }
}
