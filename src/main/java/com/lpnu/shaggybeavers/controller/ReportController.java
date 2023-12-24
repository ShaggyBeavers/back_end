package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.dto.ReportCreateDTO;
import com.lpnu.shaggybeavers.dto.ReportReadDTO;
import com.lpnu.shaggybeavers.facade.ReportFacade;
import com.lpnu.shaggybeavers.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportFacade reportFacade;

    @PostMapping("/")
    public ResponseEntity<ReportReadDTO> createReport(
            @AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody ReportCreateDTO reportCreateDTO) {
        ReportReadDTO reportReadDTO = reportFacade.createReport(userPrincipal, reportCreateDTO);
        return new ResponseEntity<>(reportReadDTO, HttpStatus.CREATED);
    }

}
