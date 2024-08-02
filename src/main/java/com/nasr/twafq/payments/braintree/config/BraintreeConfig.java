// package com.nasr.twafq.payments.braintree.config;

// import com.braintreegateway.BraintreeGateway;
// import com.braintreegateway.Environment;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class BraintreeConfig {

//     @Value("${braintree.environment}")
//     private String environment;

//     @Value("${braintree.merchantId}")
//     private String merchantId;

//     @Value("${braintree.publicKey}")
//     private String publicKey;

//     @Value("${braintree.privateKey}")
//     private String privateKey;

//     @Bean
//     public BraintreeGateway braintreeGateway() {
//         Environment env = "production".equalsIgnoreCase(environment) ? Environment.PRODUCTION : Environment.SANDBOX;
//         return new BraintreeGateway(env, merchantId, publicKey, privateKey);
//     }
// }
