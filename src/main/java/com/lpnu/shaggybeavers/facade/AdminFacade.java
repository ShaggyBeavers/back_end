package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.ModeratorDTO;
import com.lpnu.shaggybeavers.dto.RegionalModeratorDTO;
import com.lpnu.shaggybeavers.dto.UserDTO;
import com.lpnu.shaggybeavers.exception.DuplicateException;
import com.lpnu.shaggybeavers.exception.NotExistsObjectException;
import com.lpnu.shaggybeavers.filter.UserFilter;
import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.model.UserRegion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AdminFacade {

    private final UserFacade userFacade;

    private final RoleFacade roleFacade;

    private final RegionFacade regionFacade;

    private final UserRegionFacade userRegionFacade;

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

        dto.getRegionIds()
                .forEach(regionId -> {
                    Optional<UserRegion> oldUserRegion = userRegionFacade.findByUserIdAndRegionId(dto.getUserId(), regionId);

                    if (oldUserRegion.isEmpty()) {
                        UserRegion newUserRegion = new UserRegion();
                        newUserRegion.setUser(user);
                        newUserRegion.setRegion(regionFacade.findById(regionId));
                        userRegionFacade.save(newUserRegion);
                    } else {
                        throw new DuplicateException("UserRegion object already exists");
                    }
                });
    }

    @Transactional
    public void createModerator(ModeratorDTO dto) {
        User user = userFacade.getUserById(dto.getUserId());
        user.setRole(roleFacade.findByName("MODERATOR"));
        userFacade.save(user);
    }

}
