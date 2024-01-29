package com.lpnu.shaggybeavers.init;

import com.lpnu.shaggybeavers.domain.RoleEnum;
import com.lpnu.shaggybeavers.facade.RoleFacade;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class initDataBaseRole {

    private final RoleFacade roleFacade;

    @Transactional
    @PostConstruct
    public void init() {
        if (!roleFacade.existsByName(RoleEnum.ADMIN.name())) {
            roleFacade.save(RoleEnum.ADMIN.name());
        }
        if (!roleFacade.existsByName(RoleEnum.REGIONAL_MODERATOR.name())) {
            roleFacade.save(RoleEnum.REGIONAL_MODERATOR.name());
        }
        if (!roleFacade.existsByName(RoleEnum.MODERATOR.name())) {
            roleFacade.save(RoleEnum.MODERATOR.name());
        }
        if (!roleFacade.existsByName(RoleEnum.USER.name())) {
            roleFacade.save(RoleEnum.USER.name());
        }
    }

}
