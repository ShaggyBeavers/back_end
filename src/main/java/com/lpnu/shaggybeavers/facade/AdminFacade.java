package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.UserDTO;
import com.lpnu.shaggybeavers.exception.NotExistsObjectException;
import com.lpnu.shaggybeavers.filter.UserFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AdminFacade {

    private final UserFacade userFacade;

    @Transactional
    public List<UserDTO> getModerators() {
        UserFilter userFilter = new UserFilter();
        userFilter.setRoleName("ADMIN");
        return userFacade.getUsersByFilter(userFilter);
    }

    @Transactional
    public void deleteModeratorById(Long moderatorId) {
        if (!Objects.equals(userFacade.getUserById(moderatorId).getRole().getName(), "ADMIN")) {
            throw new NotExistsObjectException("Moderator with id " + moderatorId + " does not exist");
        }
        userFacade.deleteUserById(moderatorId);
    }

}
