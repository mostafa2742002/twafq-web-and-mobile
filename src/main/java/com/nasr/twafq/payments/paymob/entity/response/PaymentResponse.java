package com.nasr.twafq.payments.paymob.entity.response;
import java.time.LocalDateTime;
import java.util.*;
import lombok.Data;

@Data
public class PaymentResponse {
    private List<PaymentKey> payment_keys;
    private String id;
    private IntentionDetail intention_detail;
    private String client_secret;
    private List<PaymentMethod> payment_methods;
    private boolean confirmed;
    private String status;
    private LocalDateTime created;
    private String object;

}
