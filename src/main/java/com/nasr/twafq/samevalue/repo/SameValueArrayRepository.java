package com.nasr.twafq.samevalue.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nasr.twafq.samevalue.entity.SameValueArray;

import java.util.Optional;

public interface SameValueArrayRepository extends MongoRepository<SameValueArray, String> {
    Optional<SameValueArray> findByIndex(int index);
}
