package com.nasr.twafq.user.repo;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.nasr.twafq.user.dto.UserFilterRequest;
import com.nasr.twafq.user.entity.User;

@Repository
public interface UserRepositoryCustom {
    Page<User> findFilteredUsers(UserFilterRequest filterRequest);
}
