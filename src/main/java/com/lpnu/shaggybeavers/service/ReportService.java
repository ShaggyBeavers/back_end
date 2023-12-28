package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.model.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface ReportService extends CRUDService<Report,Long> {

    Page<Report> findAll(Pageable pageable, Specification<Report> specification);

}
