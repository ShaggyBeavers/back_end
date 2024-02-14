package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.ReportCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportCategoryRepository extends JpaRepository<ReportCategory, Long> {

    List<ReportCategory> findAllByReportId(Long reportId);

}
