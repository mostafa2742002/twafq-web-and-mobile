package com.nasr.twafq.payments.paypal.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nasr.twafq.payments.paypal.entity.UserPaypalPayment;

@Repository
public interface UserPaypalPaymentRepository extends MongoRepository<UserPaypalPayment, String> {
    UserPaypalPayment findByUserId(String userId);
    UserPaypalPayment findByPaymentId(String paymentId);
    UserPaypalPayment findByTargetId(String targetId);

}
