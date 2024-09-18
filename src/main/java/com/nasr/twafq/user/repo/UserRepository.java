package com.nasr.twafq.user.repo;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nasr.twafq.user.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {
        User findByEmail(String username);

}
