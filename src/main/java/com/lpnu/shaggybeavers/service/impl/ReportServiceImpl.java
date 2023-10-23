package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.Report;
import com.lpnu.shaggybeavers.repository.ReportRepository;
import com.lpnu.shaggybeavers.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl extends CRUDServiceImpl<Report,Long> implements ReportService {

    private final ReportRepository repository;

    @Override
    protected JpaRepository<Report, Long> getRepository () {
        return this.repository;
    }
}
