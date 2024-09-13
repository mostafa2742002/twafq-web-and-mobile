package com.nasr.twafq.user.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nasr.twafq.user.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String username);

    @Query("{ $and: [ " +
            "{ 'nationality': { $regex: ?0, $options: 'i' } }, " +
            "{ 'residence': { $regex: ?1, $options: 'i' } }, " +
            "{ 'familyStatus': { $regex: ?2, $options: 'i' } }, " +
            "{ 'gender': { $regex: ?3, $options: 'i' } }, " +
            "{ 'city': { $regex: ?4, $options: 'i' } }, " +
            "{ 'religion': { $regex: ?5, $options: 'i' } }, " +
            "{ 'marriageType': { $regex: ?6, $options: 'i' } }, " +
            "{ 'age': { $gte: ?7, $lte: ?8 } }" +
            "]}")
    Page<User> findFilteredUsers(String nationality, String residence, String familyStatus, String gender,
            String city, String religion, String marriageType, Integer minAge, Integer maxAge,
            Pageable pageable);

}
