package com.nasr.twafq.payments.paypal.config;

import com.paypal.sdk.*;
import com.paypal.sdk.authentication.ClientCredentialsAuthModel;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaypalClientConfig {

    @Value("${PAYPAL_CLIENT_ID}")
    private String clientId;

    @Value("${PAYPAL_CLIENT_SECRET}")
    private String clientSecret;

    @Bean
    public PaypalServerSdkClient paypalClient() {
        return new PaypalServerSdkClient.Builder()
                .loggingConfig(builder -> builder
                        .level(Level.DEBUG)
                        .requestConfig(log -> log.body(true))
                        .responseConfig(log -> log.headers(true)))
                .httpClientConfig(config -> config.timeout(0))
                .environment(Environment.SANDBOX)
                .clientCredentialsAuth(new ClientCredentialsAuthModel.Builder(clientId, clientSecret).build())
                .build();
    }
}
