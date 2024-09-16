package com.nasr.twafq.user.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nasr.twafq.user.dto.UserDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Document(collection = "users")
public class User extends AuditableBase implements UserDetails {

    @Id
    private String id;

    @NotNull(message = "username shouldn't be null")
    @Pattern(regexp = "^[a-zA-Z0-9 ]{3,30}$", message = "username must be between 3 and 30 characters long and can only contain letters and numbers")
    private String username;

    private String firstName;
    private String lastName;

    @Email(message = "invalid email address")
    @NotNull(message = "email shouldn't be null")
    private String email;

    private String password;
    private String confirmPassword;

    @Schema(hidden = true)
    private String token;
    private String image;
    @Schema(hidden = true)
    private boolean emailVerified;
    @Schema(hidden = true)
    private String verificationToken;
    @Schema(hidden = true)
    private String otp;

    // colors i love
    private ArrayList<Integer> ColorAnswers = new ArrayList<>();

    // Verification
    private Boolean isVerifiedUser;
    private ArrayList<String> usersContactWith;

    @Pattern(regexp = "^[0-9]{11}$", message = "invalid mobile number entered")
    @NotNull(message = "phone shouldn't be null")
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

    private boolean isChecked;

    public User(UserDTO userDTO) {
        this.email = userDTO.getEmail();
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
        this.phone = userDTO.getPhone();

        // Add all additional attributes
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.age = userDTO.getAge();
        this.weight = userDTO.getWeight();
        this.height = userDTO.getHeight();
        this.gender = userDTO.getGender();
        this.skinColor = userDTO.getSkinColor();
        this.shape = userDTO.getShape();
        this.health = userDTO.getHealth();
        this.nationality = userDTO.getNationality();
        this.country = userDTO.getCountry();
        this.city = userDTO.getCity();
        this.residence = userDTO.getResidence();
        this.familyStatus = userDTO.getFamilyStatus();
        this.marriageType = userDTO.getMarriageType();
        this.children = userDTO.getChildren();
        this.educationLevel = userDTO.getEducationLevel();
        this.work = userDTO.getWork();
        this.financialStatus = userDTO.getFinancialStatus();
        this.religion = userDTO.getReligion();
        this.doctrine = userDTO.getDoctrine();
        this.religiousCommitment = userDTO.getReligiousCommitment();
        this.smoking = userDTO.getSmoking();
        this.alcoholDrugs = userDTO.getAlcoholDrugs();
        this.selfDescription = userDTO.getSelfDescription();
        this.partnerDescription = userDTO.getPartnerDescription();
        // this.isChecked = userDTO.isChecked();
        this.ColorAnswers = userDTO.getColorAnswers();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
