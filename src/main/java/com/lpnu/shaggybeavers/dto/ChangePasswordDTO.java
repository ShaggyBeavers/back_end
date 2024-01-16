package com.lpnu.shaggybeavers.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordDTO {

    @NotBlank(message = "Password mustn't be blank")
    private String password;

    @NotBlank(message = "Password confirmation mustn't be blank")
    private String passwordConfirmation;

}
