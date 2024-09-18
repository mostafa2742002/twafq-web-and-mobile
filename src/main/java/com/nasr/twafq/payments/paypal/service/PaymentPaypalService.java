package com.nasr.twafq.payments.paypal.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nasr.twafq.payments.paypal.entity.PaypalOrder;
import com.nasr.twafq.payments.paypal.entity.UserPaypalPayment;
import com.nasr.twafq.payments.paypal.repo.UserPaypalPaymentRepository;
import com.nasr.twafq.user.entity.User;
import com.nasr.twafq.user.repo.UserRepository;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentPaypalService {

    private final PaypalService paypalService;
    private final UserRepository userRepository;
    private final UserPaypalPaymentRepository userPaypalPaymentRepository;

    // Method for verifying a user
    public ResponseEntity<String> createVerifyIntent(String userId, Integer amount) throws PayPalRESTException {
        if (userRepository.findById(userId).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }

        amount = Math.max(amount, 0);
        PaypalOrder order = new PaypalOrder(amount, "USD", "paypal", "sale", "Verify User");

        Payment payment = paypalService.createPayment(
                order.getPrice(),
                order.getCurrency(),
                order.getMethod(),
                order.getIntent(),
                "Verify User",
                "http://localhost:8080/payment/cancel",
                "http://localhost:8080/payment/success/verify");

        String approvalUrl = payment.getLinks().stream()
                .filter(link -> link.getRel().equals("approval_url"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Approval URL not found"))
                .getHref();

        // Save the payment intent with verification
        String paymentId = payment.getId();
        UserPaypalPayment userPayment = new UserPaypalPayment(userId, paymentId, null); // No targetId for verification
        userPaypalPaymentRepository.save(userPayment);

        return ResponseEntity.ok(approvalUrl);
    }

    // Method for adding a user to contacts
    public ResponseEntity<String> createAddUserIntent(String userId, Integer amount, String targetId)
            throws PayPalRESTException {
        if (userRepository.findById(userId).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }

        amount = Math.max(amount, 0);
        PaypalOrder order = new PaypalOrder(amount, "USD", "paypal", "sale", "Add User to Contacts");

        Payment payment = paypalService.createPayment(
                order.getPrice(),
                order.getCurrency(),
                order.getMethod(),
                order.getIntent(),
                "Add User to Contacts",
                "http://localhost:8080/payment/cancel",
                "http://localhost:8080/payment/success/add-user");

        String approvalUrl = payment.getLinks().stream()
                .filter(link -> link.getRel().equals("approval_url"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Approval URL not found"))
                .getHref();

        // Save the payment intent with add user intent
        String paymentId = payment.getId();
        UserPaypalPayment userPayment = new UserPaypalPayment(userId, paymentId, targetId); // targetId for add user
        userPaypalPaymentRepository.save(userPayment);

        return ResponseEntity.ok(approvalUrl);
    }

    // Handle verification success
    public ResponseEntity<String> handleVerifySuccess(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = paypalService.executePayment(paymentId, payerId);
        if (payment.getState().equals("approved")) {
            UserPaypalPayment userPayment = userPaypalPaymentRepository.findByPaymentId(paymentId);

            if (userPayment == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment record not found");
            }

            // Mark the user as verified
            User user = userRepository.findById(userPayment.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            user.setIsVerifiedUser(true);
            userRepository.save(user);

            // Remove payment record after verification
            userPaypalPaymentRepository.delete(userPayment);

            return ResponseEntity.ok("User verified successfully");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment failed");
    }

    // Handle add user success
    public ResponseEntity<String> handleAddUserSuccess(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = paypalService.executePayment(paymentId, payerId);
        if (payment.getState().equals("approved")) {
            UserPaypalPayment userPayment = userPaypalPaymentRepository.findByPaymentId(paymentId);

            if (userPayment == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment record not found");
            }

            // Add the target user to the user's contacts
            User user = userRepository.findById(userPayment.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            user.getUsersContactWith().add(userPayment.getTargetId());
            userRepository.save(user);

            // Remove payment record after adding contact
            userPaypalPaymentRepository.delete(userPayment);

            return ResponseEntity.ok("User added to contacts successfully");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment failed");
    }
}
