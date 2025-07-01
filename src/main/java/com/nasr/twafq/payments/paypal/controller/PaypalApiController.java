package com.nasr.twafq.payments.paypal.controller;

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

@RestController
@RequestMapping("/api/paypal")
@CrossOrigin(origins = "*")
public class PaypalApiController {

    private final PaypalService paypalService;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @Value("${PAYPAL_CLIENT_ID}")
    private String clientId;

    @Autowired
    public PaypalApiController(PaypalService paypalService, ObjectMapper objectMapper, UserRepository userRepository) {
        this.paypalService = paypalService;
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }

    @GetMapping("/client-id")
    public Map<String, String> getClientId() {
        return Map.of("clientId", clientId);
    }

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody Map<String, String> request) {
        try {
            String userId = request.get("userId");
            String paymentType = request.get("paymentType");
            String targetId = request.get("targetId");
            String amount = request.get("amount");

            if (userId == null || paymentType == null || amount == null) {
                throw new IllegalArgumentException("Missing required parameters");
            }
            if ("addUser".equals(paymentType) && targetId == null) {
                throw new IllegalArgumentException("Target ID required for addUser payment type");
            }

            User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
            if ("verify".equals(paymentType) && user.getIsVerifiedUser()) {
                System.out.println("the user is :");
                System.out.println(user);
                return ResponseEntity.badRequest().body("User Already Verified");
            }
            if ("addUser".equals(paymentType) && user.getUsersContactWith().contains(targetId)) {
                return ResponseEntity.badRequest().body("User Already Added");
            }

            String referenceId = userId + ":" + paymentType + ":" + targetId;
            OrderRequest orderRequest = new OrderRequest.Builder(
                CheckoutPaymentIntent.CAPTURE,
                Arrays.asList(
                    new PurchaseUnitRequest.Builder(
                        new AmountWithBreakdown.Builder("USD", amount).build()
                    ).referenceId(referenceId).build()
                )
            ).build();
            CreateOrderInput createOrderInput = new CreateOrderInput.Builder(null, orderRequest).build();
            Order order = paypalService.createOrder(createOrderInput);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/orders/{orderID}/capture")
    public ResponseEntity<String> captureOrder(@PathVariable String orderID) {
        try {
            Order capturedOrder = paypalService.captureOrder(orderID);
            String referenceId = capturedOrder.getPurchaseUnits().get(0).getReferenceId();
            String[] parts = referenceId.split(":");
            String userId = parts[0];
            String paymentType = parts[1];
            String targetId = parts.length > 2 ? parts[2] : null;

            User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
            if ("verify".equals(paymentType)) {
                user.setIsVerifiedUser(true);
            } else if ("addUser".equals(paymentType)) {
                user.getUsersContactWith().add(targetId);
            }
            userRepository.save(user);
            return ResponseEntity.ok("Transaction successful");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Transaction failed: " + e.getMessage());
        }
    }
}