package com.lpnu.shaggybeavers.init;

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
        if (!roleFacade.existsByName("USER")) {
            roleFacade.save("USER");
        }

        if (!roleFacade.existsByName("ADMIN")) {
            roleFacade.save("ADMIN");
        }
    }


}
