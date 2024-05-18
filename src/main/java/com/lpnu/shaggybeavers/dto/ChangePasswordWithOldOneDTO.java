package com.lpnu.shaggybeavers.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangePasswordWithOldOneDTO {

    @NotBlank(message = "Old password must not be blank.")
    private String oldPassword;

    @NotBlank(message = "New password must not be blank.")
    private String newPassword;

}
