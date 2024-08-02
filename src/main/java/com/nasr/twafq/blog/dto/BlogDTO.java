package com.nasr.twafq.blog.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO {

    @Id
    private String id;

    @NotNull
    private String title;
    @NotNull
    private String content;
    private String image;

}
