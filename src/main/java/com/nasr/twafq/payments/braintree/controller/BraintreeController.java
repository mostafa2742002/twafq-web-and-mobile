// package com.nasr.twafq.payments.braintree.controller;

// import io.swagger.v3.oas.annotations.parameters.RequestBody;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import com.nasr.twafq.payments.braintree.entity.BuyCourseDTO;
// import com.nasr.twafq.payments.braintree.service.BraintreeService;

// @RestController
// @RequestMapping("/braintree")
// public class BraintreeController {

//     private final BraintreeService braintreeService;

//     public BraintreeController(BraintreeService braintreeService) {
//         this.braintreeService = braintreeService;
//     }

//     @GetMapping("/token")
//     public String getToken() {
//         return braintreeService.generateClientToken();
//     }

//     @PostMapping("/checkout")
//     public ResponseEntity<String> checkout(@RequestBody BuyCourseDTO buyCourseDTO) {
//         String nonce = buyCourseDTO.getNonce();
//         String amount = buyCourseDTO.getAmount();
//         String userId = buyCourseDTO.getUserId();
//         String courseId = buyCourseDTO.getCourseId();
//         String discountCode = buyCourseDTO.getDiscountCode();
//         Double discountWallet = buyCourseDTO.getDiscountWallet();
//         int expiryDate = buyCourseDTO.getExpiryDate();
//         ResponseEntity<String> result = braintreeService.createTransaction(nonce, amount, userId, courseId, discountCode, discountWallet,expiryDate);
//         return result;
//     }
// }
