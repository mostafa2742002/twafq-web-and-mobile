package com.nasr.twafq.blog.dto;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.util.List;
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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    private String image;

    private List<SentenceDTO> sentences; // List of sentences
}