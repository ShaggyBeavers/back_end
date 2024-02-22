package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.MuseumCreateDTO;
import com.lpnu.shaggybeavers.factory.MuseumFactory;
import com.lpnu.shaggybeavers.model.Museum;
import com.lpnu.shaggybeavers.service.MuseumService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MuseumFacade {

    private final MuseumFactory museumFactory;

    private final MuseumService museumService;

    @Transactional
    public Museum findById(Long museumId) { return museumService.findById(museumId);
    }

    @Transactional
    public void createMuseum(MuseumCreateDTO museumCreateDTO) {
        museumService.save(museumFactory.toMuseum(museumCreateDTO));
    }
}
