package com.nasr.twafq.colors.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nasr.twafq.colors.entity.DataPair;

import java.util.Optional;

public interface DataPairRepository extends MongoRepository<DataPair, String> {
    Optional<DataPair> findByKey1AndKey2(int key1, int key2);
}
