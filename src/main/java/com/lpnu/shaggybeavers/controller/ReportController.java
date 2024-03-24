package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.domain.ReportStatus;
import com.lpnu.shaggybeavers.dto.ReportDTO;
import com.lpnu.shaggybeavers.dto.ReportPageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.lpnu.shaggybeavers.dto.ReportCreateDTO;
import com.lpnu.shaggybeavers.facade.ReportFacade;
import com.lpnu.shaggybeavers.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequestMapping(value = "/api/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportFacade reportFacade;

    @GetMapping("/")
    public ResponseEntity<Page<ReportPageDTO>> getReportsPage(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(reportFacade.getReportsPage(userPrincipal, pageable), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Long> createReport(
            @AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody ReportCreateDTO reportCreateDTO) {
        Long id = reportFacade.createReport(userPrincipal, reportCreateDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("/{reportId}")
    @PreAuthorize("@securityFacade.checkIfUserHasEnoughAuthorityToReadReport(authentication, #reportId)")
    public ResponseEntity<ReportDTO> getReport(@PathVariable Long reportId) {
        return new ResponseEntity<>(reportFacade.getReport(reportId), HttpStatus.OK);
    }

    @PutMapping("/status")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'REGIONAL_MODERATOR', 'MODERATOR') and @securityFacade.checkIfUserHasEnoughAuthorityOnReport(authentication, #reportId)")
    public ResponseEntity<Void> changeStatus(@RequestParam Long reportId, @RequestParam String status) {
        reportFacade.changeStatus(reportId, ReportStatus.valueOf(status));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{reportId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'REGIONAL_MODERATOR', 'MODERATOR') and @securityFacade.checkIfUserHasEnoughAuthorityOnReport(authentication, #reportId)")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long reportId) {
        reportFacade.delete(userPrincipal.getUser(), reportId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}