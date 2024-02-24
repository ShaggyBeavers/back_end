package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.domain.RelicStatus;
import com.lpnu.shaggybeavers.model.Relic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface RelicService extends FileStoreService<Relic, String, Long> {

    Page<Relic> findAll(Pageable pageable);

    Page<Relic> findAll(Specification<Relic> specification, Pageable pageable);

    Long countByStatuses(List<RelicStatus> statuses);

    Relic getCurrentFavoriteRelic();

    Relic getNewFavoriteRelic();

    Page<Relic> findAllByNameContaining(String relicName, Pageable pageable);
}
