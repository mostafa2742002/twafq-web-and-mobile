package com.nasr.twafq.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sentence {
    private String title;
    private String sentence;
    private Link link;
}
