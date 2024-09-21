package com.nasr.twafq.user.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nasr.twafq.user.entity.Story;

@Repository
public interface StoryRepository extends MongoRepository<Story, String> {

}
