package com.nasr.twafq.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "LoginDTO",
        description = "Schema to Send Login Information to the server"
)
@Data @AllArgsConstructor
public class LoginDTO {

    @Schema(
            description = "Email of the user"
    )
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Provide a valid email")
    private String email;

    @Schema(
            description = "Password of the user"
    )
    @NotBlank(message = "Password is mandatory")
    private String password;
}
