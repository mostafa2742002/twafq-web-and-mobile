package com.nasr.twafq.color.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.nasr.twafq.color.entity.Color;

public interface ColorRepository extends MongoRepository<Color, String> {
    Color findByHexCode(String hexCode);
}
