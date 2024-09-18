package com.nasr.twafq.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

import com.nasr.twafq.blog.entity.PageResponse;
import com.nasr.twafq.dto.ResponseDto;
import com.nasr.twafq.email.EmailService;
import com.nasr.twafq.jwt.JwtResponse;
import com.nasr.twafq.jwt.JwtService;
import com.nasr.twafq.user.dto.LoginDTO;
import com.nasr.twafq.user.dto.UserDTO;
import com.nasr.twafq.user.dto.UserFilterRequest;
import com.nasr.twafq.user.entity.User;
import com.nasr.twafq.user.entity.UserAlgorithm;
import com.nasr.twafq.user.entity.UserPersentage;
import com.nasr.twafq.user.repo.UserAlgorithmRepository;
import com.nasr.twafq.user.repo.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;
    private final EmailService emailService;
    private final ColorAlgorithm colorAlgorithm;
    private final UserAlgorithmRepository userAlgorithmRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        return user;
    }

    public ResponseDto register(@NonNull UserDTO userDTO) throws MessagingException, InterruptedException {
        System.out.println(userDTO.getColorAnswers());
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new IllegalArgumentException("User already exists");
        }
        userDTO.setEmail(userDTO.getEmail().toLowerCase());
        User user = new User(userDTO);
        String verificationToken = jwtService.generateToken(user);

        user.setEmailVerified(false);
        user.setVerificationToken(verificationToken);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);
        String subject = "Verify Your Email";

        colorAlgorithm.calculateColorPresentage(savedUser.getId(), userDTO.getColorAnswers());
        // if we use render site then use this
        // String body = "Click the link to verify your
        // email:https://overseas-vivie-sasa-deploy-1402ab77.koyeb.app/api/verifyemail?token="
        // + verificationToken;

        // if we use localhost then use this
        String body = "Click the link to verify your email:http://localhost:8080/api/verifyemail?token="
                + verificationToken;
        emailService.sendEmail(savedUser.getEmail(), subject, body);

        String msg = "the user added successfully go to your email to verify your email";
        return new ResponseDto("200", msg);
    }

    public JwtResponse login(@NonNull LoginDTO userDTO) {
        userDTO.setEmail(userDTO.getEmail().toLowerCase());
        User user = userRepository.findByEmail(userDTO.getEmail());
        if (user != null && bCryptPasswordEncoder.matches(userDTO.getPassword(), user.getPassword())) {

            if (user.isEmailVerified() == false)
                throw new IllegalArgumentException("Email not verified");

            return new JwtResponse(jwtService.generateToken(user), jwtService.generateRefreshToken(user), user);
        }
        throw new IllegalArgumentException("Invalid credentials");
    }

    public User findUserByEmail(String email) {
        if (userRepository.findByEmail(email) == null) {
            throw new IllegalArgumentException("User not found");
        }
        return userRepository.findByEmail(email);
    }

    public void saveProfileImage(String email, String image) {
        User user = findUserByEmail(email);
        user.setImage(image);
        userRepository.save(user);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userRepository.findByEmail(userDetails.getUsername());
    }

    public String refreshToken(String refreshToken) {
        String email = jwtService.extractUserName(refreshToken);
        if (email == null) {
            throw new RuntimeException("Invalid Token");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        if (!jwtService.validateToken(refreshToken, userDetails)) {
            throw new RuntimeException("expired Token or Invalid");
        }

        return jwtService.generateToken(userDetails);
    }

    public ResponseEntity<String> verifyEmail(String token) {
        String email = jwtService.extractUserName(token);
        User user = userRepository.findByEmail(email);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User user1 = user;
        if (user1.getVerificationToken().equals(token)) {
            user1.setEmailVerified(true);
            user1.setVerificationToken(null);
            userRepository.save(user1);

            return ResponseEntity.ok("Email verified successfully");

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Token");
        }
    }

    public ResponseEntity<String> updateProfile(UserDTO user, String userId) {

        // Retrieve the user from the repository
        User existingUser = userRepository.findById(userId).orElse(null);

        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        if (user.getFirstName() != null && !user.getFirstName().isEmpty())
            existingUser.setFirstName(user.getFirstName());

        if (user.getLastName() != null && !user.getLastName().isEmpty())
            existingUser.setLastName(user.getLastName());

        if (user.getPhone() != null && !user.getPhone().isEmpty())
            existingUser.setPhone(user.getPhone());

        if (user.getEmail() != null && !user.getEmail().isEmpty())
            existingUser.setEmail(user.getEmail());

        if (user.getPassword() != null && !user.getPassword().isEmpty())
            existingUser.setPassword(user.getPassword()); // Be cautious with password updates, consider adding
                                                          // validation

        // Update additional attributes if present
        if (user.getGender() != null)
            existingUser.setGender(user.getGender());

        if (user.getNationality() != null)
            existingUser.setNationality(user.getNationality());

        if (user.getCountry() != null)
            existingUser.setCountry(user.getCountry());

        if (user.getCity() != null)
            existingUser.setCity(user.getCity());

        if (user.getMarriageType() != null)
            existingUser.setMarriageType(user.getMarriageType());

        if (user.getFamilyStatus() != null)
            existingUser.setFamilyStatus(user.getFamilyStatus());

        if (user.getAge() > 0)
            existingUser.setAge(user.getAge());

        if (user.getChildren() > 0)
            existingUser.setChildren(user.getChildren());

        if (user.getWeight() > 0)
            existingUser.setWeight(user.getWeight());

        if (user.getHeight() > 0)
            existingUser.setHeight(user.getHeight());

        if (user.getSkinColor() != null)
            existingUser.setSkinColor(user.getSkinColor());

        if (user.getShape() != null)
            existingUser.setShape(user.getShape());

        if (user.getWork() != null)
            existingUser.setWork(user.getWork());

        if (user.getEducationLevel() != null)
            existingUser.setEducationLevel(user.getEducationLevel());

        if (user.getFinancialStatus() != null)
            existingUser.setFinancialStatus(user.getFinancialStatus());

        if (user.getReligion() != null)
            existingUser.setReligion(user.getReligion());

        if (user.getSelfDescription() != null)
            existingUser.setSelfDescription(user.getSelfDescription());

        if (user.getPartnerDescription() != null)
            existingUser.setPartnerDescription(user.getPartnerDescription());

        // Save the updated user profile
        userRepository.save(existingUser);

        return ResponseEntity.ok("Profile updated successfully");
    }

    public ResponseEntity<String> updatePassword(String user_id, String oldPassword, String newPassword) {
        User user = userRepository.findById(user_id).orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        if (!bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Old password is incorrect");
        }

        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.save(user);

        return ResponseEntity.ok("Password updated successfully");
    }

    public User getProfile(@NotNull String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return user;
    }

    public String forgotPassword(@NotNull String email) throws MessagingException, InterruptedException {

        // random otp from 5 digits
        int otp = (int) (Math.random() * (99999 - 10000 + 1) + 10000);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        user.setOtp(String.valueOf(otp));
        userRepository.save(user);

        String subject = "Reset Password";
        String body = "Your OTP is: " + otp;

        emailService.sendEmail(email, subject, body);

        return "OTP sent to your email";
    }

    public void resetPassword(@NotNull String userEmail, @NotNull String otp, @NotNull String newPassword) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (!user.getOtp().equals(otp)) {
            throw new IllegalArgumentException("Invalid OTP");
        }

        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        user.setOtp(null);
        userRepository.save(user);
    }

    public Page<User> findAllUsers(UserFilterRequest filterRequest) {
        return userRepository.findFilteredUsers(filterRequest);
    }

    public ArrayList<UserPersentage> findUsersLikeMe(String userId) {
        UserAlgorithm user = userAlgorithmRepository.findByUserId(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        ArrayList<UserPersentage> usersLikeMe = user.getUsersLikeMe();
        // we need only the top 10 users
        if (usersLikeMe.size() > 10) {
            usersLikeMe = new ArrayList<>(usersLikeMe.subList(0, 10));
        }
        return usersLikeMe;

    }

    public Page<User> getUsersSortedByAge(int page, int size, String sortDirection) {
        Pageable pageable;
        if (sortDirection.equalsIgnoreCase("desc")) {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "age"));
        } else {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "age"));
        }
        return userRepository.findAll(pageable);
    }

    public Page<UserPersentage> getAllUsersLikeMe(String userId, int page, int size, String sortDirection) {
        UserAlgorithm userAlgorithm = userAlgorithmRepository.findByUserId(userId);

        if (userAlgorithm == null) {
            throw new RuntimeException("User algorithm not found");
        }

        List<UserPersentage> usersLikeMe = userAlgorithm.getUsersLikeMe();

        // Sort the list based on the required direction
        if (sortDirection.equalsIgnoreCase("asc")) {
            Collections.reverse(usersLikeMe); // Reverse the list if asc is required
        }

        // Pagination logic
        int start = Math.min(page * size, usersLikeMe.size());
        int end = Math.min((page + 1) * size, usersLikeMe.size());

        List<UserPersentage> paginatedList = usersLikeMe.subList(start, end);
        return new PageImpl<>(paginatedList);
    }
}
