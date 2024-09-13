package com.nasr.twafq.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserFilterRequest {

    private String nationality;
    private String countryOfResidence;
    private String maritalStatus;
    private String gender;
    private String city;
    private String religion;
    private String familyStatus;
    private String marriageType;
    private Integer minAge;
    private Integer maxAge;
    private int page = 0;   // Default page
    private int size = 10;  // Default size

}
