package com.nasr.twafq.user.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nasr.twafq.blog.entity.PageResponse;
import com.nasr.twafq.constants.ServerConstants;
import com.nasr.twafq.dto.ErrorResponseDto;
import com.nasr.twafq.dto.ResponseDto;
import com.nasr.twafq.user.dto.LoginDTO;
import com.nasr.twafq.user.dto.PasswordDTO;
import com.nasr.twafq.user.dto.UserDTO;
import com.nasr.twafq.user.entity.User;
import com.nasr.twafq.user.entity.UserPersentage;
import com.nasr.twafq.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
public class UserController {

    private UserService userService;

    @Operation(summary = "Sign up a new user", description = "Sign up a new user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Failed to send verification email", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid @NotNull UserDTO userDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(userDTO));
        } catch (MessagingException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send verification email");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Sign in a user", description = "Sign in a user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User signed in successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody @Valid @NotNull LoginDTO userDTO) {
        try {
            return ResponseEntity.ok(userService.login(userDTO));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @Operation(summary = "Get user profile", description = "Get user profile by user id")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "Profile retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)

            )) })
    @GetMapping("/profile")
    public ResponseEntity<User> getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String email = ((UserDetails) principal).getUsername();
        return ResponseEntity.ok(userService.getProfile(email));
    }

    @Operation(summary = "Validate The user email", description = "Validate The user email")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "Email verified successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid token", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))

            ) })

    @GetMapping("/verifyemail")
    public ResponseEntity<String> verifyEmail(@RequestParam @NotNull String token) {
        return userService.verifyEmail(token);
    }

    @Operation(summary = "Get a new access token", description = "send a refresh token to get a new access token")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "Token refreshed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid token", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)

            )) })

    @GetMapping("/refresh-token")
    public String postMethodName(@RequestParam @NotNull String refreshToken) {
        return userService.refreshToken(refreshToken);
    }

    @Operation(summary = "update user profile", description = "update user profile by user id")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "Profile updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)

            )) })

    @PutMapping("/profile")
    public ResponseEntity<String> updateProfile(@RequestBody @NotNull UserDTO user, @RequestParam String user_id) {
        return userService.updateProfile(user, user_id);
    }

    @Operation(summary = "Update user password", description = "Update user password")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "Password updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)

            )) })

    @PutMapping("/password")
    public ResponseEntity<String> updatePassword(@RequestBody @NotNull PasswordDTO passwordDTO) {

        return userService.updatePassword(passwordDTO.getUserId(), passwordDTO.getOldPassword(),
                passwordDTO.getNewPassword());
    }

    @Operation(summary = "Forgot password", description = "Forgot password")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "otp sent successfully to you email", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)

            )) })
    @PostMapping("/forgotpassword")
    public ResponseEntity<ResponseDto> forgotPassword(@RequestParam @NotNull String email)
            throws MessagingException, InterruptedException {
        String response = userService.forgotPassword(email);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(ServerConstants.STATUS_200, response));
    }

    @Operation(summary = "Put the opt", description = "Reset password by by user email and the otp")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "Password reset successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)

            )) })
    @PutMapping("/resetpassword")
    public ResponseEntity<ResponseDto> resetPassword(@RequestParam @NotNull String userEmail,
            @RequestParam @NotNull String otp,
            @RequestParam @NotNull String newPassword) {
        userService.resetPassword(userEmail, otp, newPassword);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(ServerConstants.STATUS_200, ServerConstants.MESSAGE_200));
    }

    @Operation(summary = "Fetch all Users REST API", description = "REST API to fetch all User and Filter them by Page and Size and other parameters")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/users")
    public ResponseEntity<PageResponse<User>> findAllUsers(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(defaultValue = "null", required = false) String nationality,
            @RequestParam(defaultValue = "null",required = false) String countryOfResidence,
            @RequestParam(defaultValue = "null" ,required = false) String maritalStatus) {
        PageResponse<User> users = userService.findAllUsers(page, size,nationality,countryOfResidence,maritalStatus);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(users);
    }

    @Operation(summary = "Fetch all Users Like Me REST API", description = "REST API to fetch all Users Like Me")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/users/like-me")
    public ResponseEntity<ArrayList<UserPersentage>> findUsersLikeMe(
            @RequestParam String userId) {
        ArrayList<UserPersentage> users = userService.findUsersLikeMe(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(users);
    }


}
