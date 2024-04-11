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

    @Query(value = "select r from Relic r where r.isDeleted = false and r.favorite = false and r.relicFiles is not empty order by random() limit 1")
    Relic getNewFavoriteRelic();

    @Query(value = "select count(r) from Relic r where r.isDeleted = false and r.status IN :statuses")
    Long countByStatuses(@Param("statuses") List<RelicStatus> statuses);

    @Query("select r from Relic r where r.isDeleted = false and r.id = :id")
    @NonNull Optional<Relic> findById(@Param("id") @NonNull Long id);

    @Query("select r from Relic r where r.id = :id")
    Optional<Relic> findByIdAbsolute(@Param("id") @NonNull Long id);

    @Query("select r from Relic r where r.isDeleted = false and r.museum.id = :museumId")
    boolean existsByMuseumId(@Param("museumId") @NonNull Long museumId);

    @Query("select r from Relic r where r.isDeleted = false and r.region.id = :regionId")
    boolean existsByRegionId(@Param("regionId") @NonNull Long regionId);

    @Query("select r from Relic r where r.isDeleted = false and r.creationPlace.id = :creationPlaceId")
    boolean existsByCreationPlaceId(@Param("creationPlaceId") @NonNull Long creationPlaceId);
}
