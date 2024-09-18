package com.nasr.twafq.user.dto;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
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
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Password must be at least 8 characters long and contain at least one letter and one number")
    private String password;

    private String confirmPassword;

    @Pattern(regexp = "^[a-zA-Z0-9 ]{3,30}$", message = "username must be between 3 and 30 characters long and can only contain letters and numbers")
    private String username;
    private String firstName;
    private String lastName;
    private String phone;

    private int age;
    private int weight;
    private int height;

    // Updated attributes as List<String> to handle array-like inputs
    private List<String> gender = new ArrayList<>();
    private List<String> skinColor = new ArrayList<>();
    private List<String> shape = new ArrayList<>();
    private List<String> health = new ArrayList<>();
    private List<String> nationality = new ArrayList<>();
    private List<String> country = new ArrayList<>();
    private List<String> city = new ArrayList<>();
    private List<String> residence = new ArrayList<>();
    private List<String> familyStatus = new ArrayList<>();
    private List<String> marriageType = new ArrayList<>();
    private List<String> educationLevel = new ArrayList<>();
    private List<String> financialStatus = new ArrayList<>();
    private List<String> religion = new ArrayList<>();
    private List<String> doctrine = new ArrayList<>();
    private List<String> religiousCommitment = new ArrayList<>();
    private List<String> smoking = new ArrayList<>();
    private List<String> alcoholDrugs = new ArrayList<>();

    private int children;

    private String work;
    private String selfDescription;
    private String partnerDescription;

    // private boolean isChecked;

    // Hidden fields
    @Schema(hidden = true)
    private ArrayList<String> usersLikeMe = new ArrayList<>();

    // @Schema(hidden = true)
    private ArrayList<Integer> ColorAnswers = new ArrayList<>();

    @Schema(hidden = true)
    private Boolean isVerifiedUser;
}
