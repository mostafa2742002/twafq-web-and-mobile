package com.nasr.twafq.user.dto;

import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.units.qual.N;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @Pattern(regexp = "^.{8,}$", message = "Password must be at least 8 characters long.")
    private String password;

    private String confirmPassword;

    @Pattern(regexp = "^[a-zA-Z0-9 ]{3,30}$", message = "username must be between 3 and 30 characters long and can only contain letters and numbers")
    private String username;
    @NotNull(message = "First name is mandatory")
    private String firstName;
    @NotNull(message = "Last name is mandatory")
    private String lastName;
    @NotNull(message = "Phone is mandatory")
    private String phone;

    @NotNull(message = "age is mandatory")
    private int age;
    @NotNull(message = "weight is mandatory")
    private int weight;
    @NotNull(message = "height is mandatory")
    private int height;

    // Updated attributes as List<String> to handle array-like inputs
    @NotNull(message = "nationality is mandatory")
    private List<String> gender = new ArrayList<>();
    @NotNull(message = "nationality is mandatory")
    private List<String> skinColor = new ArrayList<>();
    @NotNull(message = "nationality is mandatory")
    private List<String> shape = new ArrayList<>();
    @NotNull(message = "nationality is mandatory")
    private List<String> health = new ArrayList<>();
    @NotNull(message = "nationality is mandatory")
    private List<String> nationality = new ArrayList<>();
    @NotNull(message = "nationality is mandatory")
    private List<String> country = new ArrayList<>();
    @NotNull(message = "nationality is mandatory")
    private List<String> city = new ArrayList<>();
    @NotNull(message = "nationality is mandatory")
    private List<String> residence = new ArrayList<>();
    @NotNull(message = "nationality is mandatory")
    private List<String> familyStatus = new ArrayList<>();
    @NotNull(message = "nationality is mandatory")
    private List<String> marriageType = new ArrayList<>();
    @NotNull(message = "nationality is mandatory")
    private List<String> educationLevel = new ArrayList<>();
    @NotNull(message = "nationality is mandatory")
    private List<String> financialStatus = new ArrayList<>();
    @NotNull(message = "nationality is mandatory")
    private List<String> religion = new ArrayList<>();
    @NotNull(message = "nationality is mandatory")
    private List<String> doctrine = new ArrayList<>();
    @NotNull(message = "nationality is mandatory")
    private List<String> religiousCommitment = new ArrayList<>();
    @NotNull(message = "nationality is mandatory")
    private List<String> smoking = new ArrayList<>();
    @NotNull(message = "nationality is mandatory")
    private List<String> alcoholDrugs = new ArrayList<>();

    @NotNull(message = "nationality is mandatory")
    private int children;

    @NotNull(message = "nationality is mandatory")
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
