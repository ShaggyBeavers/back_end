package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.domain.RelicStatus;
import com.lpnu.shaggybeavers.exception.NotEqualObjectsException;
import com.lpnu.shaggybeavers.model.Relic;
import com.lpnu.shaggybeavers.repository.RelicRepository;
import com.lpnu.shaggybeavers.service.RelicService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelicServiceImpl extends CRUDServiceImpl<Relic, Long> implements RelicService {

    private final RelicRepository repository;

    @Lazy
    public RelicServiceImpl(RelicRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Relic, Long> getRepository () {
        return this.repository;
    }

    @Override
    @Transactional
    public Page<Relic> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Relic> findAll(Specification<Relic> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }

    @Override
    public Long countByStatuses(List<RelicStatus> statuses) {
        return repository.countByStatuses(statuses);
    }

    @Override
    public Relic getCurrentFavoriteRelic() {
        return repository.getCurrentFavoriteRelic();
    }

    @Override
    public Relic getNewFavoriteRelic() {
        return repository.getNewFavoriteRelic();
    }

    @Override
    public Page<Relic> findAllByNameContaining(String relicName, Pageable pageable) {
        return repository.findByNameIgnoreCaseContaining(relicName, pageable);
    }

    @Override
    public Relic findByIdAbsolute(Long relicId) {
        return repository.findByIdAbsolute(relicId).orElseThrow(NotEqualObjectsException::new);
    }

    @Override
    public boolean existsByMuseumId(Long museumId) {
        return repository.existsByMuseumId(museumId);
    }

    @Override
    public boolean existsByRegionId(Long regionId) {
        return repository.existsByRegionId(regionId);
    }

    @Override
    public boolean existsByCreationPlaceId(Long regionId) {
        return repository.existsByCreationPlaceId(regionId);
    }

}
