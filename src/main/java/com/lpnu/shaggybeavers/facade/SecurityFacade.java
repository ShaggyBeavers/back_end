package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.domain.RoleEnum;
import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.security.UserPrincipal;
import com.lpnu.shaggybeavers.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

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
                        && !(UserUtil.doesUserHaveRole(user, RoleEnum.MODERATOR) && doesUserAndUserHaveCommonRegionAndCategory(currentUser.getId(), userId))) {
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

        if (!UserUtil.doesUserHaveRole(currentUser, RoleEnum.ADMIN)) {
            return doesUserAndRelicHaveCommonRegionAndCategory(currentUser.getId(), relicId);
        }

        return true;
    }

    public boolean checkIfUserHasEnoughAuthorityOnReport(Authentication authentication, Long reportId) {
        User currentUser = ((UserPrincipal) authentication.getPrincipal()).getUser();

        if (!UserUtil.doesUserHaveRole(currentUser, RoleEnum.ADMIN)) {
            return doesUserAndReportHaveCommonRegionAndCategory(currentUser.getId(), reportId);
        }

        return true;
    }

    public boolean checkIfUserHasEnoughAuthorityToReadReport(Authentication authentication, Long reportId) {
        User currentUser = ((UserPrincipal) authentication.getPrincipal()).getUser();

        if (UserUtil.doesUserHaveRole(currentUser, RoleEnum.USER)) {
            return Objects.equals(Long.toString(currentUser.getId()), reportFacade.getReport(reportId).getUserId());
        }
        else if (!UserUtil.doesUserHaveRole(currentUser, RoleEnum.ADMIN)) {
            return doesUserAndReportHaveCommonRegionAndCategory(currentUser.getId(), reportId);
        }

        return true;
    }

    private boolean doesUserAndUserHaveCommonRegionAndCategory(Long userId, Long entityId) {
        Set<Long> userRegions = userRegionFacade.getRegionIdsByUserId(userId);
        Set<Long> userCategories = userCategoryFacade.getCategoryIdsByUserId(userId);

        Set<Long> entityRegions = userRegionFacade.getRegionIdsByUserId(entityId);
        Set<Long> entityCategories = userCategoryFacade.getCategoryIdsByUserId(entityId);

        return userRegions.stream().anyMatch(entityRegions::contains)
                && userCategories.stream().anyMatch(entityCategories::contains);
    }

    private boolean doesUserAndRelicHaveCommonRegionAndCategory(Long userId, Long entityId) {
        Set<Long> userRegions = userRegionFacade.getRegionIdsByUserId(userId);
        Set<Long> userCategories = userCategoryFacade.getCategoryIdsByUserId(userId);

        Long entityRegion = relicFacade.findById(entityId).getRegion().getId();
        Set<Long> entityCategories = relicCategoryFacade.getCategoryIdsByRelicId(entityId);

        return userRegions.stream().anyMatch(entityRegion::equals)
                && userCategories.stream().anyMatch(entityCategories::contains);
    }

    private boolean doesUserAndReportHaveCommonRegionAndCategory(Long userId, Long entityId) {
        Set<Long> userRegions = userRegionFacade.getRegionIdsByUserId(userId);
        Set<Long> userCategories = userCategoryFacade.getCategoryIdsByUserId(userId);

        Long entityRegion = reportFacade.findById(entityId).getRegion().getId();
        Set<Long> entityCategories = reportCategoryFacade.getCategoryIdsByReportId(entityId);

        return userRegions.stream().anyMatch(entityRegion::equals)
                && userCategories.stream().anyMatch(entityCategories::contains);
    }

}
