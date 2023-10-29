package com.lpnu.shaggybeavers.auth;

import lombok.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    private String email;

    private String password;

}
