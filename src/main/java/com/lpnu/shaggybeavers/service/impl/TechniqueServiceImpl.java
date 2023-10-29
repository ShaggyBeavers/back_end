package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.Technique;
import com.lpnu.shaggybeavers.repository.TechniqueRepository;
import com.lpnu.shaggybeavers.service.TechniqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TechniqueServiceImpl extends CRUDServiceImpl<Technique, Long> implements TechniqueService {

    private final TechniqueRepository techniqueRepository;

    @Override
    protected JpaRepository<Technique, Long> getRepository() {
        return this.techniqueRepository;
    }
}
