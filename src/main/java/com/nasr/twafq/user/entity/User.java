package com.nasr.twafq.user.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nasr.twafq.user.dto.UserDTO;

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
    private String name;

    private String firstName;
    private String lastName;

    @Email(message = "invalid email address")
    @NotNull(message = "email shouldn't be null")
    private String email;

    private String password;

    @Pattern(regexp = "^[0-9]{11}$", message = "invalid mobile number entered ")
    @NotNull(message = "phone shouldn't be null")
    private String phone;

    private String token;
    private String image;
    private boolean emailVerified;
    private String verificationToken;
    private String otp;

    // colors i love
    private ArrayList<Integer> ColorAnswers = new ArrayList<>();

    // Verification
    private Boolean isVerifiedUser;


    private ArrayList<String> usersContactWith;

    // Additional attributes
    private String gender; // Male or Female
    private String nationality; // Country of birth
    private String countryOfResidence; // Country of residence
    private String city; // City of residence
    private String marriageType; // Marriage type for male -> )الزوجة األولى – الثانية – الثالثة – الرابعة( for
                                 // women -> )ال امانع في التعدد – يجب ان أكون الزوجة الوحيدة(
    private String maritalStatus; // for male -> )أعزب – مطلق – أرم – أرمل( for
    private int age; // Age
    private int numberOfChildren;
    private int weight;
    private int height;
    private String skinColor;
    private String bodyType; // • بنية الجسم )نحيف – متوسط – سمين – رياضي(
    private String job;
    private String education;
    private String financialStatus; // • الوضع المادي )جيد – متوسط – ضعيف(
    private String monthlyIncome;
    private String healthStatus;
    private String religion; // • الديانة )مسلم – مسيحي – يهودي – غير ذلك(
    private String selfDescription;
    private String partnerPreferences;
    private boolean smoking;

    public User(UserDTO userDTO) {
        this.email = userDTO.getEmail();
        this.name = userDTO.getName();
        this.password = userDTO.getPassword();
        this.phone = userDTO.getPhone();
        // Add all additional attributes
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
