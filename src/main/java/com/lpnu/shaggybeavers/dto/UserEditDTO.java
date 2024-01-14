package com.lpnu.shaggybeavers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserEditDTO {

    @Email(message = "Email is wrong, check your input on correcting")
    @NotBlank(message = "message mustn't be blank")
    private String email;

    @NotBlank(message = "firstName mustn't be blank ")
    private String firstName;

    @NotBlank(message = "lastName mustn't be blank ")
    private String lastName;

}
