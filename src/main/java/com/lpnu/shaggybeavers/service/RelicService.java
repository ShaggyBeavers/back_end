package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.model.Relic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface RelicService extends CRUDService<Relic,Long> {

    Page<Relic> findAll(Pageable pageable);

    List<Relic> findAll(Specification<Relic> specification);
}
