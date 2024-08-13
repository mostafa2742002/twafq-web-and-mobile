package com.nasr.twafq.user.dto;

import java.util.ArrayList;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Provide a valid email")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    private String name;
    private String firstName;
    private String lastName;
    private String phone;

    // users like me
    @Schema(hidden = true)
    private ArrayList<String> usersLikeMe = new ArrayList<>();
    // colors i love
    @Schema(hidden = true)
    private ArrayList<Integer> ColorAnswers = new ArrayList<>();

    // Verification
    @Schema(hidden = true)
    private Boolean isVerifiedUser;

    // Additional attributes
    private String gender;
    private String nationality;
    private String countryOfResidence;
    private String city;
    private String marriageType;
    private String maritalStatus;
    private int age;
    private int numberOfChildren;
    private int weight;
    private int height;
    private String skinColor;
    private String bodyType;
    private String job;
    private String education;
    private String financialStatus;
    private String monthlyIncome;
    private String healthStatus;
    private String religion;
    private String selfDescription;
    private String partnerPreferences;
    private boolean smoking;
}
