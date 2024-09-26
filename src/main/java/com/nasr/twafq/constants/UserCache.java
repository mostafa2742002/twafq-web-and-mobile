package com.nasr.twafq.constants;

import com.nasr.twafq.user.repo.UserRepository;

import jakarta.annotation.PostConstruct;

import com.nasr.twafq.user.entity.User;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Component
public class UserCache {

    private UserRepository userRepository;
    private List<User> users;

    @PostConstruct
    public void init() {
        users = userRepository.findAll();
    }

    public List<User> getUsers() {
        return users;
    }

    public void refresh() {
        users = userRepository.findAll();
    }

}
