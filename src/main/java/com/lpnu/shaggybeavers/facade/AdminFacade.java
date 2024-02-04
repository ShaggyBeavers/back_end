package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.domain.RoleEnum;
import com.lpnu.shaggybeavers.dto.ChangeModeratorCategoriesDTO;
import com.lpnu.shaggybeavers.dto.ChangeModeratorRegionsDTO;
import com.lpnu.shaggybeavers.dto.ModeratorCreateDTO;
import com.lpnu.shaggybeavers.dto.UserDTO;
import com.lpnu.shaggybeavers.filter.UserFilter;
import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdminFacade {

    private final UserFacade userFacade;

    private final RoleFacade roleFacade;

    private final UserRegionFacade userRegionFacade;

    private final UserCategoryFacade userCategoryFacade;

    @Transactional
    public void createModerator(User currentUser, ModeratorCreateDTO dto, RoleEnum roleEnum) {
        User user = userFacade.getUserById(dto.getUserId());
        user.setRole(roleFacade.findByName(roleEnum.name()));
        userFacade.save(user);

        switch (RoleEnum.valueOf(currentUser.getRole().getName())) {
            case ADMIN -> {
                userRegionFacade.create(user, dto.getRegionIds());
                userCategoryFacade.create(user, dto.getCategoryIds());
            }
            case REGIONAL_MODERATOR -> {
                userRegionFacade.create(user, userRegionFacade.getRegionIdsByUserId(currentUser.getId()));
                userCategoryFacade.create(user, userCategoryFacade.getCategoryIdsByUserId(currentUser.getId()));
            }
        }
    }

    @Transactional
    public List<UserDTO> getModerators(User currentUser, RoleEnum roleEnum) {
        UserFilter userFilter = new UserFilter();

        userFilter.setRoleName(roleEnum.name());

        if (UserUtil.doesUserHasRole(currentUser, RoleEnum.REGIONAL_MODERATOR)) {
            userFilter.setRegionIds(userRegionFacade.getRegionIdsByUserId(currentUser.getId()));
        }

        return userFacade.getUsersByFilter(userFilter);
    }

    @Transactional
    public void changeModeratorRegions(ChangeModeratorRegionsDTO dto) {
        userRegionFacade.deleteAllByUserId(dto.getModeratorId());
        userRegionFacade.create(userFacade.getUserById(dto.getModeratorId()), dto.getRegionIds());
    }

    @Transactional
    public void changeModeratorCategories(ChangeModeratorCategoriesDTO dto) {
        userCategoryFacade.deleteAllByUserId(dto.getModeratorId());
        userCategoryFacade.create(userFacade.getUserById(dto.getModeratorId()), dto.getCategoryIds());
    }

    @Transactional
    public void deleteModeratorById(Long moderatorId) {
        userFacade.deleteUserById(moderatorId);
    }

}
