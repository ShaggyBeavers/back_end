package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long>, JpaSpecificationExecutor<Report> {

    @Query("select r from Report r where r.isDeleted = false")
    @NonNull Page<Report> findAll(@NonNull Pageable pageable, @NonNull Specification<Report> specification);

    @Query("select r from Report r where r.isDeleted = false")
    @NonNull Page<Report> findAll(@NonNull Pageable pageable);

    @Query("select r from Report r where r.isDeleted = false and r.id = :id")
    @NonNull Optional<Report> findById(@Param("id") @NonNull Long id);

    @Query("select r from Report r where r.id = :id")
    Optional<Report> findByIdAbsolute(@Param("id") @NonNull Long id);

    @Query("SELECT DISTINCT r FROM Report r " +
            "LEFT JOIN FETCH r.reportCategories rc " +
            "WHERE r.isDeleted = FALSE " +
            "AND ((r.region.id IN :userRegions OR r.region.id IS NULL) " +
            "AND (rc.report.id IS NULL OR rc.category.id IN :userCategories) " +
            "OR (r.user.id = :userId))")
    Page<Report> getModeratorReports(Set<Long> userRegions, Set<Long> userCategories, Long userId, Pageable pageable);

    @Query("SELECT DISTINCT r FROM Report r " +
            "LEFT JOIN FETCH r.reportCategories rc " +
            "WHERE r.isDeleted = FALSE " +
            "AND ((r.region.id IN :userRegions OR r.region.id IS NULL) " +
            "OR (r.user.id = :userId))")
    Page<Report> getRegionalModeratorReports(Set<Long> userRegions, Long userId, Pageable pageable);

    @Query("select r from Report r where r.isDeleted = false and r.user.id = :userId")
    Page<Report> findAllByUserId(Long userId, Pageable pageable);

    @Query("select case when count(r) > 0 then true else false end from Report r where r.isDeleted = false and r.region.id = :regionId")
    boolean existsByRegionId(@Param("regionId") @NonNull Long regionId);

}
