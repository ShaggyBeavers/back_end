package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.facade.ReportCategoryFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/report-categories")
@RequiredArgsConstructor
public class ReportCategoryController {

    private final ReportCategoryFacade reportCategoryFacade;

}