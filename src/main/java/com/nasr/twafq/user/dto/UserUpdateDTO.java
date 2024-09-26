package com.nasr.twafq.user.dto;

import lombok.Data;
import java.util.List;

@Data
public class UserUpdateDTO {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private List<String> gender;
    private List<String> nationality;
    private List<String> country;
    private List<String> city;
    private List<String> marriageType;
    private List<String> familyStatus;
    private int age;
    private int children;
    private int weight;
    private int height;
    private List<String> skinColor;
    private List<String> shape;
    private String work;
    private List<String> educationLevel;
    private List<String> financialStatus;
    private List<String> religion;
    private String selfDescription;
    private String partnerDescription;
}
