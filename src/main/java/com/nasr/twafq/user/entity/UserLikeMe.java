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
@Document(collection = "usersLikeMe")
public class UserLikeMe {

    @Id
    private String id;

    private String userId;
    
}
