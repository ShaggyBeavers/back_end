package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.domain.RoleEnum;
import com.lpnu.shaggybeavers.exception.NotExistsObjectException;
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

    public boolean checkIfRegionalModeratorControlsModerator(Authentication authentication, Long moderatorId) {
        User moderator = userFacade.getUserById(moderatorId);
        User currentUser = ((UserPrincipal) authentication.getPrincipal()).getUser();

        if (UserUtil.doesUserHasRole(currentUser, RoleEnum.REGIONAL_MODERATOR)) {
            Long regionalModeratorId = currentUser.getId();

            Set<Long> moderatorRegions = userRegionFacade.getRegionIdsByUserId(moderatorId);
            Set<Long> moderatorCategories = userCategoryFacade.getCategoryIdsByUserId(moderatorId);

            Set<Long> regionalModeratorRegions = userRegionFacade.getRegionIdsByUserId(regionalModeratorId);
            Set<Long> regionalModeratorCategories = userCategoryFacade.getCategoryIdsByUserId(regionalModeratorId);

            if(UserUtil.doesUserHasRole(moderator, RoleEnum.REGIONAL_MODERATOR)
                    || moderatorRegions.stream().noneMatch(regionalModeratorRegions::contains)
                    || moderatorCategories.stream().noneMatch(regionalModeratorCategories::contains)) {
                throw new NotExistsObjectException("Moderator is out of your control");
            }
        }

        return true;
    }

}
