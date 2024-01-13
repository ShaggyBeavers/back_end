package com.lpnu.shaggybeavers.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordDTO {

    private String token;

    private String password;

    private String passwordConfirmation;

}
