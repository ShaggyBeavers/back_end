package com.lpnu.shaggybeavers.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleEnum {
    ADMIN,
    REGIONAL_MODERATOR,
    MODERATOR,
    USER
}
