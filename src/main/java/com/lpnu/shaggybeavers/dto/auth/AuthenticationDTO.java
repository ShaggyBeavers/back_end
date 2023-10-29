package com.lpnu.shaggybeavers.dto.auth;

import lombok.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDTO {

    private String email;

    private String password;

}
