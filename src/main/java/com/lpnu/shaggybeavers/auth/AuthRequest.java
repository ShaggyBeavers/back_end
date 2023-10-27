package com.lpnu.shaggybeavers.auth;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    private String email;

    private String password;

}
