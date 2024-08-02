package com.nasr.twafq.user.entity;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "userAlgorithm")
public class UserAlgorithm {

    @Id
    private String id;

    private String userId;
    private ArrayList<Integer> answer = new ArrayList<>();
    private ArrayList<UserPersentage> usersLikeMe = new ArrayList<>();
}

// to do : replace the string to object -> userid and presentage.