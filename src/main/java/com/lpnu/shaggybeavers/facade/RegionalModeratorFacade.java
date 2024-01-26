package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.ModeratorCreateDTO;
import com.lpnu.shaggybeavers.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RegionalModeratorFacade {

    private final UserFacade userFacade;

    private final RoleFacade roleFacade;

    private final UserCategoryFacade userCategoryFacade;

    private final UserRegionFacade userRegionFacade;

    @Transactional
    public void createModerator(ModeratorCreateDTO dto, Long regionalModeratorId) {
        User user = userFacade.getUserById(dto.getUserId());
        user.setRole(roleFacade.findByName("MODERATOR"));
        userFacade.save(user);

        userRegionFacade.create(user, userRegionFacade.getRegionIdsByUserId(regionalModeratorId));
        userCategoryFacade.create(user, dto.getCategoryIds());
    }

}
