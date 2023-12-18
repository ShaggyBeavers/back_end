package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.Relic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RelicRepository extends JpaRepository<Relic, Long>, JpaSpecificationExecutor<Relic> {

    @Override
    Page<Relic> findAll(Pageable pageable);
}
