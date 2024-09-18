package com.nasr.twafq.user.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFilterRequest {

    private List<String> nationality = new ArrayList<>();
    private List<String> countryOfResidence = new ArrayList<>();
    private List<String> maritalStatus = new ArrayList<>();
    private List<String> gender = new ArrayList<>();
    private List<String> city = new ArrayList<>();
    private List<String> religion = new ArrayList<>();
    private List<String> familyStatus = new ArrayList<>();
    private List<String> marriageType = new ArrayList<>();
    private Integer minAge;
    private Integer maxAge;
    private int page = 0;   // Default page
    private int size = 10;  // Default size
}
