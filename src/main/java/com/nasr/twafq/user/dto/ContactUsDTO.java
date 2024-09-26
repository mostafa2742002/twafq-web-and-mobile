package com.nasr.twafq.user.dto;

import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactUsDTO {

    private String userId ;
    private String message;
    private String intent;
}
