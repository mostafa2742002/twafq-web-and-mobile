package com.nasr.twafq.payments.paymob.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nasr.twafq.payments.paymob.entity.user.UserPayment;

@Repository
public interface UserPaymentRepository extends MongoRepository<UserPayment, String> {

        UserPayment findByPaymentId(String paymentId);

        UserPayment findByUserId(String userId);

        void deleteByPaymentId(String paymentId);

        void deleteByUserId(String userId);

}
