package com.lpnu.shaggybeavers.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JwtDTO {
    private String accessToken;
}
