package com.lpnu.shaggybeavers.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {

    @NotBlank(message = "firstName mustn't be blank ")
    private String firstName;

    @NotBlank(message = "lastName mustn't be blank ")
    private String lastName;

    @Email(message = "Email is wrong, check your input on correcting")
    @NotBlank(message = "message mustn't be blank")
    private String email;

    @NotBlank(message = "password mustn't be blank ")
    private String password;

}
