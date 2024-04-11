package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.exception.AssociationException;
import com.lpnu.shaggybeavers.model.Technique;
import com.lpnu.shaggybeavers.repository.TechniqueRepository;
import com.lpnu.shaggybeavers.service.RelicInfoService;
import com.lpnu.shaggybeavers.service.TechniqueService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class TechniqueServiceImpl extends CRUDServiceImpl<Technique, Long> implements TechniqueService {

    private final RelicInfoService relicInfoService;

    private final TechniqueRepository techniqueRepository;

    @Lazy
    public TechniqueServiceImpl(RelicInfoService relicInfoService, TechniqueRepository techniqueRepository) {
        this.relicInfoService = relicInfoService;
        this.techniqueRepository = techniqueRepository;
    }

    @Override
    protected JpaRepository<Technique, Long> getRepository() {
        return this.techniqueRepository;
    }

    @Override
    @Transactional
    public Technique deleteById(Long techniqueId) {
        Technique technique = findById(techniqueId);

        if (relicInfoService.existsByTechniqueId(techniqueId)) {
            throw new AssociationException("The technique is associated with a relic info");
        }

        techniqueRepository.delete(technique);
        return technique;
    }
}
