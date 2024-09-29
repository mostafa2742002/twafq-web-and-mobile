package com.nasr.twafq.payments.paymob.entity.user;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "userPayments")
public class UserPayment {

    @Id
    private String id;

    private String userId;
    // private String courseId;
    private String email;
    private String intent;
    private String targetId;
    private String paymentId;
    // private LocalDate expiryDate;
}
