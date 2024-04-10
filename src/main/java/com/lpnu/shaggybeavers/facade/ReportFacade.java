package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.domain.ReportStatus;
import com.lpnu.shaggybeavers.domain.RoleEnum;
import com.lpnu.shaggybeavers.dto.*;
import com.lpnu.shaggybeavers.exception.FileException;
import com.lpnu.shaggybeavers.factory.ReportFactory;
import com.lpnu.shaggybeavers.filter.ReportFilter;
import com.lpnu.shaggybeavers.model.*;
import com.lpnu.shaggybeavers.security.UserPrincipal;
import com.lpnu.shaggybeavers.service.ReportService;
import com.lpnu.shaggybeavers.specification.ReportSpecification;
import com.lpnu.shaggybeavers.util.FileUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ReportFacade {

    private final ReportFactory reportFactory;

    private final ReportService reportService;
  
    private final CategoryFacade categoryFacade;

    private final ReportCategoryFacade reportCategoryFacade;

    private final UserCategoryFacade userCategoryFacade;

    private final UserRegionFacade userRegionFacade;

    private final RegionFacade regionFacade;

    private final ReportFileFacade reportFileFacade;

    @Transactional
    public Long createReport(UserPrincipal userPrincipal, ReportCreateDTO dto) {
        Report report = reportFactory.toReport(dto);
        report.setStatus(ReportStatus.NEW);
        var region = (dto.getRegionId() != null) ? regionFacade.findById((dto.getRegionId())) : null;
        report.setRegion(region);
        report.setUser(userPrincipal.getUser());
        reportService.save(report);

        dto.getCategoryIds()
                .forEach(categoryId -> {
                    Category category = categoryFacade.findById(categoryId);
                    ReportCategory reportCategory = new ReportCategory();
                    reportCategory.setReport(report);
                    reportCategory.setCategory(category);
                    reportCategoryFacade.save(reportCategory);
                });

        return report.getId();
    }

    @Transactional
    public ReportDTO getReport(Long reportId) {
        return reportFactory.toReportDTO(reportService.findById(reportId));
    }

    @Transactional
    public void changeStatus(Long reportId, ReportStatus status) {
        Report report = reportService.findById(reportId);
        report.setStatus(status);
        reportService.save(report);
    }

    @Transactional
    public Page<ReportPageDTO> getReportsPage(UserPrincipal userPrincipal, Pageable pageable) {
        Long userId = userPrincipal.getId();
        String currentUserRole = userPrincipal.getUser().getRole().getName();
        ReportFilter reportFilter = new ReportFilter();

        reportFilter.setUserRole(RoleEnum.valueOf(currentUserRole));
        reportFilter.setUserId(userId);
        Specification<Report> specificationOnId = new ReportSpecification(reportFilter);
        reportFilter.setUserId(null);

        switch (RoleEnum.valueOf(currentUserRole)) {
            case MODERATOR -> {
                reportFilter.setCategoryIds(userCategoryFacade.getCategoryIdsByUserId(userId));
                reportFilter.setRegionIds(userRegionFacade.getRegionIdsByUserId(userId));
            }
            case REGIONAL_MODERATOR -> {
                reportFilter.setRegionIds(userRegionFacade.getRegionIdsByUserId(userId));
            }
            case ADMIN -> {
                Page<Report> reportPage = reportService.findAll(pageable);
                return reportPage.map(reportFactory::toReportPageDTO);
            }
        }

        Specification<Report> specificationOnAuthority = new ReportSpecification(reportFilter);
        Specification<Report> specification = specificationOnAuthority.or(specificationOnId);
        Page<Report> reportPage = reportService.findAll(pageable, specification);
        return reportPage.map(reportFactory::toReportPageDTO);
    }

    @Transactional
    public Report findById(Long reportId) {
        return reportService.findById(reportId);
    }

    @Transactional
    public void delete(Long reportId) {
        Report report = reportService.findById(reportId);

//        switch (RoleEnum.valueOf(currentUser.getRole().getName())) {
//            case ADMIN -> reportService.delete(report);
//            case REGIONAL_MODERATOR, MODERATOR -> {
//                report.setDeleted(true);
//                reportService.update(report);
//            }
//        }

        report.setDeleted(true);
    }

    @Transactional
    public void uploadFiles(Long reportId, List<MultipartFile> multipartFiles) {
        multipartFiles.forEach(multipartFile -> {
            try {
                reportFileFacade.createReportFile(reportService.findById(reportId), FileUtil.toFile(multipartFile));
            } catch (IOException e) {
                throw new FileException("Failed uploading file: " + e.getMessage());
            }
        });
    }

    @Transactional
    public List<FileDTO> downloadFiles(Long reportId) {
        List<ReportFile> reportFiles = reportFileFacade.findAllByReportId(reportId);
        return reportFiles.stream()
                .map(reportFile -> reportFactory.toFileDTO(reportFileFacade.downloadFile(reportFile), reportFile.getExtension(), reportId))
                .toList();
    }

    @Transactional
    public void deleteFiles(Long reportId) {
        reportFileFacade.deleteAllByReportId(reportId);
    }

    @Transactional
    public void updateFiles(Long reportId, List<MultipartFile> multipartFiles) {
        deleteFiles(reportId);
        uploadFiles(reportId, multipartFiles);
    }

    @Transactional
    public void restore(Long reportId) {
        Report report = reportService.findByIdAbsolute(reportId);
        report.setDeleted(false);
    }
  
    public List<FileDTO> downloadFiles(DownloadFilesDTO dto) {
        return dto.getEntityIds().stream()
                .map(reportId -> {
                    List<ReportFile> reportFiles = reportFileFacade.findAllByReportId(reportId);
                    if (reportFiles.isEmpty()) {
                        return null;
                    }
                    return reportFiles.get(0);
                })
                .filter(Objects::nonNull)
                .map(relicFile -> reportFactory.toFileDTO(reportFileFacade.downloadFile(relicFile), relicFile.getExtension(), relicFile.getReport().getId()))
                .toList();
    }

}