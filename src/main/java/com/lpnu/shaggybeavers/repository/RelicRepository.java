package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.domain.RelicStatus;
import com.lpnu.shaggybeavers.model.Relic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelicRepository extends JpaRepository<Relic, Long>, JpaSpecificationExecutor<Relic> {

    @Override
    Page<Relic> findAll(Pageable pageable);

    Page<Relic> findByNameIgnoreCaseContaining(String name, Pageable pageable);

    @Query(value = "SELECT r FROM Relic r WHERE r.favorite = true")
    Relic getCurrentFavoriteRelic();

    @Query(value = "SELECT r FROM Relic r WHERE r.favorite = false AND r.imageUrl != null ORDER BY RANDOM() LIMIT 1")
    Relic getNewFavoriteRelic();

    @Query(value = "SELECT COUNT(r) FROM Relic r WHERE r.status IN :statuses")
    Long countByStatuses(@Param("statuses") List<RelicStatus> statuses);

}
