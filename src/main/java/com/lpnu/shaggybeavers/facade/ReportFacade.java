package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.ReportFactory;
import com.lpnu.shaggybeavers.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportFacade {

    private final ReportFactory reportFactory;

    private final ReportService reportService;

}
