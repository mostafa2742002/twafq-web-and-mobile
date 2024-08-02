package com.nasr.twafq.user.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nasr.twafq.user.entity.UserAlgorithm;

@Repository
public interface UserAlgorithmRepository extends MongoRepository<UserAlgorithm, String> {

    UserAlgorithm findByUserId(String userId);
}
