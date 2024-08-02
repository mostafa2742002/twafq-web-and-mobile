package com.nasr.twafq.user.dto;

import lombok.Data;

@Data
public class PasswordDTO {

    private String userId;
    private String oldPassword;
    private String newPassword;
}
