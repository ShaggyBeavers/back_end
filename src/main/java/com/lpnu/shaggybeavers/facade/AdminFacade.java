package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.domain.RoleEnum;
import com.lpnu.shaggybeavers.dto.ModeratorCreateDTO;
import com.lpnu.shaggybeavers.dto.UserDTO;
import com.lpnu.shaggybeavers.exception.NotExistsObjectException;
import com.lpnu.shaggybeavers.filter.UserFilter;
import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AdminFacade {

    private final UserFacade userFacade;

    private final RoleFacade roleFacade;

    private final UserRegionFacade userRegionFacade;

    private final UserCategoryFacade userCategoryFacade;

    @Transactional
    public void createModerator(ModeratorCreateDTO dto, RoleEnum roleEnum) {
        User user = userFacade.getUserById(dto.getUserId());
        user.setRole(roleFacade.findByName(roleEnum.name()));
        userFacade.save(user);

        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUserRole = userPrincipal.getUser().getRole().getName();
        Long currentUserId = userPrincipal.getUser().getId();

        switch (RoleEnum.valueOf(currentUserRole)) {
            case ADMIN -> {
                userRegionFacade.create(user, dto.getRegionIds());
                userCategoryFacade.create(user, dto.getCategoryIds());
            }
            case REGIONAL_MODERATOR -> {
                userRegionFacade.create(user, userRegionFacade.getRegionIdsByUserId(currentUserId));
                userCategoryFacade.create(user, userCategoryFacade.getCategoryIdsByUserId(currentUserId));
            }
        }
    }

    @Transactional
    public List<UserDTO> getModerators(RoleEnum roleEnum) {
        UserFilter userFilter = new UserFilter();

        userFilter.setRoleName(roleEnum.name());

        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUserRole = userPrincipal.getUser().getRole().getName();
        Long currentUserId = userPrincipal.getUser().getId();

        if (currentUserRole.equals(RoleEnum.REGIONAL_MODERATOR.name())) {
            userFilter.setRegionIds(userRegionFacade.getRegionIdsByUserId(currentUserId));
        }

        return userFacade.getUsersByFilter(userFilter);
    }

    @Transactional
    public void deleteModeratorById(Long moderatorId) {
        if (!Objects.equals(userFacade.getUserById(moderatorId).getRole().getName(), "MODERATOR")) {
            throw new NotExistsObjectException("Moderator with id " + moderatorId + " does not exist");
        }
        userFacade.deleteUserById(moderatorId);
    }

}
