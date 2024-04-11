package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.domain.RoleEnum;
import com.lpnu.shaggybeavers.model.Report;
import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.security.UserPrincipal;
import com.lpnu.shaggybeavers.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

import static com.lpnu.shaggybeavers.domain.RoleEnum.ADMIN;

@Component
@RequiredArgsConstructor
public class SecurityFacade {

    private final UserRegionFacade userRegionFacade;

    private final UserCategoryFacade userCategoryFacade;

    private final UserFacade userFacade;

    private final RelicFacade relicFacade;

    private final RelicCategoryFacade relicCategoryFacade;

    private final ReportFacade reportFacade;

    private final ReportCategoryFacade reportCategoryFacade;

    public boolean checkIfUserHasEnoughAuthorityOnUser(Authentication authentication, Long userId) {
        User user = userFacade.getUserById(userId);
        User currentUser = ((UserPrincipal) authentication.getPrincipal()).getUser();

        switch (RoleEnum.valueOf(currentUser.getRole().getName())) {
            case REGIONAL_MODERATOR -> {
                if (!UserUtil.doesUserHaveRole(user, RoleEnum.USER)
                        && !(UserUtil.doesUserHaveRole(user, RoleEnum.MODERATOR) && (user.getUserRegions().isEmpty() || doesUserAndUserHaveCommonRegion(currentUser.getId(), userId)))) {
                    return false;
                }
            }
            case MODERATOR -> {
                if (!UserUtil.doesUserHaveRole(user, RoleEnum.USER)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean checkIfUserHasEnoughAuthorityOnRelic(Authentication authentication, Long relicId) {
        User currentUser = ((UserPrincipal) authentication.getPrincipal()).getUser();

        if (!UserUtil.doesUserHaveRole(currentUser, ADMIN)) {
            return doesUserAndRelicHaveCommonRegionAndCategory(currentUser.getId(), relicId);
        }

        return true;
    }

    public boolean checkIfUserHasEnoughAuthorityOnReport(Authentication authentication, Long reportId) {
        User currentUser = ((UserPrincipal) authentication.getPrincipal()).getUser();
        Report report = reportFacade.findById(reportId);

        if (Objects.equals(currentUser.getId(), report.getUser().getId())) {
            return true;
        }

        switch (RoleEnum.valueOf(currentUser.getRole().getName())) {
            case MODERATOR -> {
                if (Objects.nonNull(report.getRegion()) && !report.getReportCategories().isEmpty()) {
                    return doesUserAndReportHaveCommonRegion(currentUser.getId(), reportId)
                            && doesUserAndReportHaveCommonCategory(currentUser.getId(), reportId);
                }
                if (Objects.nonNull(report.getRegion())) {
                    return doesUserAndReportHaveCommonRegion(currentUser.getId(), reportId);
                }
                return doesUserAndReportHaveCommonCategory(currentUser.getId(), reportId);
            }
            case REGIONAL_MODERATOR -> {
                if (Objects.nonNull(report.getRegion())) {
                    return doesUserAndReportHaveCommonRegion(currentUser.getId(), reportId);
                }
                return true;
            }
            case ADMIN -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    private boolean doesUserAndUserHaveCommonRegion(Long userId, Long entityId) {
        Set<Long> userRegions = userRegionFacade.getRegionIdsByUserId(userId);
        Set<Long> entityRegions = userRegionFacade.getRegionIdsByUserId(entityId);

        return userRegions.stream().anyMatch(entityRegions::contains);
    }

    private boolean doesUserAndRelicHaveCommonRegionAndCategory(Long userId, Long entityId) {
        Set<Long> userRegions = userRegionFacade.getRegionIdsByUserId(userId);
        Set<Long> userCategories = userCategoryFacade.getCategoryIdsByUserId(userId);

        Long entityRegion = relicFacade.findById(entityId).getRegion().getId();
        Set<Long> entityCategories = relicCategoryFacade.getCategoryIdsByRelicId(entityId);

        return userRegions.stream().anyMatch(entityRegion::equals)
                && userCategories.stream().anyMatch(entityCategories::contains);
    }

    private boolean doesUserAndReportHaveCommonRegion(Long userId, Long entityId) {
        Set<Long> userRegions = userRegionFacade.getRegionIdsByUserId(userId);
        Long entityRegion = reportFacade.findById(entityId).getRegion().getId();

        return userRegions.stream().anyMatch(entityRegion::equals);
    }

    private boolean doesUserAndReportHaveCommonCategory(Long userId, Long entityId) {
        Set<Long> userCategories = userCategoryFacade.getCategoryIdsByUserId(userId);
        Set<Long> entityCategories = reportCategoryFacade.getCategoryIdsByReportId(entityId);

        return userCategories.stream().anyMatch(entityCategories::contains);
    }

}
