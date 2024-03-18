package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.TechniqueCreateDTO;
import com.lpnu.shaggybeavers.dto.TechniqueDTO;
import com.lpnu.shaggybeavers.factory.TechniqueFactory;
import com.lpnu.shaggybeavers.service.TechniqueService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TechniqueFacade {

    private final TechniqueFactory techniqueFactory;

    private final TechniqueService techniqueService;

    @Transactional
    public void createTechnique(TechniqueCreateDTO techniqueCreateDTO) {
        techniqueService.save(techniqueFactory.toTechnique(techniqueCreateDTO));
    }

    @Transactional
    public TechniqueDTO findById(Long techniqueId) {
        return techniqueFactory.toTechniqueDTO(techniqueService.findById(techniqueId));
    }

    @Transactional
    public List<TechniqueDTO> findAll() {
        return techniqueFactory.toTechniqueDTOs(techniqueService.findAll());
    }

}
