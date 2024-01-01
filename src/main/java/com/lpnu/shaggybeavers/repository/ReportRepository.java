package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.Relic;
import com.lpnu.shaggybeavers.model.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long>, JpaSpecificationExecutor<Report> {

}
