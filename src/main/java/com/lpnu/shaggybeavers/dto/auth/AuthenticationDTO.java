package com.lpnu.shaggybeavers.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDTO {

    @Email(message = "Email is wrong, check your input on correcting")
    @NotBlank(message = "message mustn't be blank")
    private String email;

    @NotBlank(message = "password mustn't be blank ")
    private String password;

}
