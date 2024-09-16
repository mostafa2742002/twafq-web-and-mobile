package com.nasr.twafq.blog.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "blogs")
public class Blog {

    @Id
    private String id;

    private String title;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    private String image;

    private List<Sentence> sentences;  // List of sentences with title, sentence, and link

}

