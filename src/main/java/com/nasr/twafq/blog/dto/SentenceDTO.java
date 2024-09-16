package com.nasr.twafq.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SentenceDTO {
    private String title;
    private String sentence;
    private LinkDTO link;
}