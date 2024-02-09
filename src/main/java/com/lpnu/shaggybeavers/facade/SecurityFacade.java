package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.domain.RoleEnum;
import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.security.UserPrincipal;
import com.lpnu.shaggybeavers.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class SecurityFacade {

    private final UserRegionFacade userRegionFacade;

    private final UserCategoryFacade userCategoryFacade;

    private final UserFacade userFacade;

    public boolean checkIfUserHasEnoughAuthority(Authentication authentication, Long userId) {
        User user = userFacade.getUserById(userId);
        User currentUser = ((UserPrincipal) authentication.getPrincipal()).getUser();

        switch (RoleEnum.valueOf(currentUser.getRole().getName())) {
            case REGIONAL_MODERATOR -> {
                if (!UserUtil.doesUserHaveRole(user, RoleEnum.USER)
                        && !(UserUtil.doesUserHaveRole(user, RoleEnum.MODERATOR) && doesRegionalModeratorControlModerator(currentUser.getId(), userId))) {
                    return false;
                }
            }
            case MODERATOR -> {
                if (!UserUtil.doesUserHaveRole(user, RoleEnum.USER)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean doesRegionalModeratorControlModerator(Long regionalModeratorId, Long moderatorId) {
        Set<Long> moderatorRegions = userRegionFacade.getRegionIdsByUserId(moderatorId);
        Set<Long> moderatorCategories = userCategoryFacade.getCategoryIdsByUserId(moderatorId);

        Set<Long> regionalModeratorRegions = userRegionFacade.getRegionIdsByUserId(regionalModeratorId);
        Set<Long> regionalModeratorCategories = userCategoryFacade.getCategoryIdsByUserId(regionalModeratorId);

        return moderatorRegions.stream().anyMatch(regionalModeratorRegions::contains)
                && moderatorCategories.stream().anyMatch(regionalModeratorCategories::contains);
    }

}
