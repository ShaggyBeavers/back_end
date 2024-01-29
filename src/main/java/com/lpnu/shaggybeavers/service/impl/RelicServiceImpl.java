package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.domain.RelicStatus;
import com.lpnu.shaggybeavers.model.Relic;
import com.lpnu.shaggybeavers.repository.RelicContentStore;
import com.lpnu.shaggybeavers.repository.RelicRepository;
import com.lpnu.shaggybeavers.service.RelicService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.content.commons.store.ContentStore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RelicServiceImpl extends FileStoreServiceImpl<Relic, String, Long> implements RelicService {

    private final RelicRepository repository;

    private final RelicContentStore contentStore;

    @Override
    protected JpaRepository<Relic, Long> getRepository () {
        return this.repository;
    }

    @Override
    protected ContentStore<Relic, String> getContentStore() {
        return this.contentStore;
    }

    @Override
    @Transactional
    public Page<Relic> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<Relic> findAll(Specification<Relic> specification) {
        return repository.findAll(specification);
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

}
