package com.nasr.twafq.payments.paypal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasr.twafq.payments.paypal.service.PaypalService;
import com.nasr.twafq.user.repo.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasr.twafq.payments.paypal.service.PaypalService;
import com.nasr.twafq.user.entity.User;
import com.nasr.twafq.user.repo.UserRepository;
import com.paypal.sdk.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/payments")
public class PageController {
    private final PaypalService paypalService;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @Value("${PAYPAL_CLIENT_ID}")
    private String clientId;

    @Autowired
    public PageController(PaypalService paypalService, ObjectMapper objectMapper, UserRepository userRepository) {
        this.paypalService = paypalService;
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }

    @GetMapping("/checkoutPage")
    public String paypalCheckoutPage(
            @RequestParam("userId") String userId,
            @RequestParam(name="paymentType", defaultValue="verify") String paymentType,
            @RequestParam(name="targetId", required=false) String targetId,
            @RequestParam(name="amount", defaultValue="10.00") String amount,
            HttpServletRequest request,
            Model model) {

        userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Missing or invalid Authorization header");
        }
        

        model.addAttribute("userId", userId);
        model.addAttribute("paymentType", paymentType);
        model.addAttribute("targetId", targetId);
        model.addAttribute("amount", amount);
        model.addAttribute("jwtToken", authHeader);
        return "paypal-checkout";  // Thymeleaf template: paypal-checkout.html
    }

}
