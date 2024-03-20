package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.MuseumCreateDTO;
import com.lpnu.shaggybeavers.dto.MuseumDTO;
import com.lpnu.shaggybeavers.factory.MuseumFactory;
import com.lpnu.shaggybeavers.model.Museum;
import com.lpnu.shaggybeavers.service.MuseumService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Transactional
    public MuseumDTO getById(Long museumId) {
        return museumFactory.toMuseumDTO(museumService.findById(museumId));
    }

    @Transactional
    public List<MuseumDTO> getAll() {
        return museumFactory.toMuseumDTOs(museumService.findAll());
    }

}
