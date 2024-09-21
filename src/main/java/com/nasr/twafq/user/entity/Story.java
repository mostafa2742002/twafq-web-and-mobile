package com.nasr.twafq.user.entity;

import org.checkerframework.checker.units.qual.t;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "story")
public class Story {

    @Id
    private String id;

    private String userId;

    private String story;

    private Boolean isview = true;
}   
