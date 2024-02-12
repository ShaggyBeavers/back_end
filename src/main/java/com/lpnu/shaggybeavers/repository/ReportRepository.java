package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long>, JpaSpecificationExecutor<Report> {

    @Query("select r from Report r where r.isDeleted = false and r.id = :id")
    @NonNull Optional<Report> findById(@Param("id") @NonNull Long id);

}
