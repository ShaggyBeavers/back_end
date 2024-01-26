package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.ModeratorAdminCreateDTO;
import com.lpnu.shaggybeavers.dto.RegionalModeratorDTO;
import com.lpnu.shaggybeavers.dto.UserDTO;
import com.lpnu.shaggybeavers.exception.NotExistsObjectException;
import com.lpnu.shaggybeavers.filter.UserFilter;
import com.lpnu.shaggybeavers.model.User;
import lombok.RequiredArgsConstructor;
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
    public List<UserDTO> getModerators() {
        UserFilter userFilter = new UserFilter();
        userFilter.setRoleName("MODERATOR");
        return userFacade.getUsersByFilter(userFilter);
    }

    @Transactional
    public void deleteModeratorById(Long moderatorId) {
        if (!Objects.equals(userFacade.getUserById(moderatorId).getRole().getName(), "MODERATOR")) {
            throw new NotExistsObjectException("Moderator with id " + moderatorId + " does not exist");
        }
        userFacade.deleteUserById(moderatorId);
    }

    @Transactional
    public void createRegionalModerator(RegionalModeratorDTO dto) {
        User user = userFacade.getUserById(dto.getUserId());
        user.setRole(roleFacade.findByName("REGIONAL_MODERATOR"));
        userFacade.save(user);

        userRegionFacade.create(user, dto.getRegionIds());
    }

    @Transactional
    public void createModerator(ModeratorAdminCreateDTO dto) {
        User user = userFacade.getUserById(dto.getUserId());
        user.setRole(roleFacade.findByName("MODERATOR"));
        userFacade.save(user);

        userRegionFacade.create(user, dto.getRegionIds());
        userCategoryFacade.create(user, dto.getCategoryIds());
    }

}
