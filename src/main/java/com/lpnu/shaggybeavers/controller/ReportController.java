package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.dto.CurrentUserReportDTO;
import com.lpnu.shaggybeavers.facade.ReportFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportFacade reportFacade;

    @GetMapping("/user-profile/{userId}")
    public ResponseEntity<Page<CurrentUserReportDTO>> getUserReports(
            @PathVariable(value = "userId") Long userId,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(reportFacade.getUserReports(userId, pageable), HttpStatus.OK);
    }


}
