package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.domain.RoleEnum;
import com.lpnu.shaggybeavers.dto.ChangeModeratorCategoriesDTO;
import com.lpnu.shaggybeavers.dto.ChangeModeratorRegionsDTO;
import com.lpnu.shaggybeavers.dto.ModeratorCreateDTO;
import com.lpnu.shaggybeavers.dto.UserDTO;
import com.lpnu.shaggybeavers.exception.NotExistsObjectException;
import com.lpnu.shaggybeavers.filter.UserFilter;
import com.lpnu.shaggybeavers.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;

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

        if (doesUserHasRole(currentUser, RoleEnum.REGIONAL_MODERATOR)) {
            userFilter.setRegionIds(userRegionFacade.getRegionIdsByUserId(currentUser.getId()));
        }

        return userFacade.getUsersByFilter(userFilter);
    }

    @Transactional
    public void changeModeratorRegions(User currentUser, ChangeModeratorRegionsDTO dto) {
        if (doesUserHasRole(currentUser, RoleEnum.REGIONAL_MODERATOR)) {
            checkIfModeratorBelongsToRegionalModerator(dto.getModeratorId(), currentUser.getId());
        }

        userRegionFacade.deleteAllByUserId(dto.getModeratorId());
        userRegionFacade.create(userFacade.getUserById(dto.getModeratorId()), dto.getRegionIds());
    }

    @Transactional
    public void changeModeratorCategories(User currentUser, ChangeModeratorCategoriesDTO dto) {
        if (doesUserHasRole(currentUser, RoleEnum.REGIONAL_MODERATOR)) {
            checkIfModeratorBelongsToRegionalModerator(dto.getModeratorId(), currentUser.getId());
        }

        userCategoryFacade.deleteAllByUserId(dto.getModeratorId());
        userCategoryFacade.create(userFacade.getUserById(dto.getModeratorId()), dto.getCategoryIds());
    }

    @Transactional
    public void deleteModeratorById(User currentUser, Long moderatorId) {
        User moderator = userFacade.getUserById(moderatorId);

        if (!doesUserHasRole(moderator, RoleEnum.REGIONAL_MODERATOR) &&
                !doesUserHasRole(moderator, RoleEnum.MODERATOR)) {
            throw new NotExistsObjectException("Moderator with id %s doesn't exist".formatted(moderatorId));
        }

        if (doesUserHasRole(currentUser, RoleEnum.REGIONAL_MODERATOR) &&
                doesUserHasRole(moderator, RoleEnum.MODERATOR)) {
            checkIfModeratorBelongsToRegionalModerator(moderatorId, currentUser.getId());
        }

        userFacade.deleteUserById(moderatorId);
    }

    private boolean doesUserHasRole(User user, RoleEnum roleEnum) {
        return Objects.equals(user.getRole().getName(), roleEnum.name());
    }

    private void checkIfModeratorBelongsToRegionalModerator(Long moderatorId, Long regionalModeratorId) {
        Set<Long> moderatorRegions = userRegionFacade.getRegionIdsByUserId(moderatorId);
        Set<Long> moderatorCategories = userCategoryFacade.getCategoryIdsByUserId(moderatorId);

        Set<Long> regionalModeratorRegions = userRegionFacade.getRegionIdsByUserId(regionalModeratorId);
        Set<Long> regionalModeratorCategories = userCategoryFacade.getCategoryIdsByUserId(regionalModeratorId);

        if(moderatorRegions.stream().noneMatch(regionalModeratorRegions::contains)
                || moderatorCategories.stream().noneMatch(regionalModeratorCategories::contains)) {
            throw new NotExistsObjectException("Moderator is not in your regions or not in your categories");
        }
    }

}
