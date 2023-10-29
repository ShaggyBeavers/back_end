package com.lpnu.shaggybeavers.dto.auth;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

}
