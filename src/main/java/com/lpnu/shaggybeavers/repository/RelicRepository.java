package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.domain.RelicStatus;
import com.lpnu.shaggybeavers.model.Relic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RelicRepository extends JpaRepository<Relic, Long>, JpaSpecificationExecutor<Relic> {

    @Query("select r from Relic r where r.isDeleted = false")
    @NonNull Page<Relic> findAll(@NonNull Pageable pageable);

    @Query("select r from Relic r where lower(r.name) like lower(concat('%', :name, '%')) and r.isDeleted = false")
    Page<Relic> findByNameIgnoreCaseContaining(@Param("name") String name, Pageable pageable);

    @Query(value = "select r from Relic r where r.isDeleted = false and r.favorite = true")
    Relic getCurrentFavoriteRelic();

    @Query(value = "select r from Relic r where r.isDeleted = false and r.favorite = false and r.imageUrl != null order by random() limit 1")
    Relic getNewFavoriteRelic();

    @Query(value = "select count(r) from Relic r where r.isDeleted = false and r.status IN :statuses")
    Long countByStatuses(@Param("statuses") List<RelicStatus> statuses);

    @Query("select r from Relic r where r.isDeleted = false and r.id = :id")
    @NonNull Optional<Relic> findById(@Param("id") @NonNull Long id);

}
