package com.lpnu.shaggybeavers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDTO {

    @Email(message = "Email is wrong, check your input on correcting")
    @NotBlank(message = "Message mustn't be blank")
    private String email;

}
