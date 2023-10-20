package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/report")
@RequiredArgsConstructor
public class ReportController {

    private ReportService reportService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getReportById(@PathVariable Long id) {
        return ResponseEntity.ok( reportService.findById(id));
    }

}
