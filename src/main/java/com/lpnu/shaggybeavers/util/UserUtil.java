package com.lpnu.shaggybeavers.util;

import com.lpnu.shaggybeavers.domain.RoleEnum;
import com.lpnu.shaggybeavers.model.User;

import java.util.Objects;

public class UserUtil {

    public static boolean doesUserHasRole(User user, RoleEnum roleEnum) {
        return Objects.equals(user.getRole().getName(), roleEnum.name());
    }

}
