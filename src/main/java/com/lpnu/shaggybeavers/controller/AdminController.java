package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.domain.RoleEnum;
import com.lpnu.shaggybeavers.dto.ChangeModeratorCategoriesDTO;
import com.lpnu.shaggybeavers.dto.ChangeModeratorRegionsDTO;
import com.lpnu.shaggybeavers.dto.ModeratorCreateDTO;
import com.lpnu.shaggybeavers.dto.UserDTO;
import com.lpnu.shaggybeavers.facade.AdminFacade;
import com.lpnu.shaggybeavers.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminFacade adminFacade;

    @PostMapping("/regional-moderators")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> createRegionalModerator(
            @AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody ModeratorCreateDTO dto) {
        adminFacade.createModerator(userPrincipal.getUser(), dto, RoleEnum.REGIONAL_MODERATOR);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/moderators")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'REGIONAL_MODERATOR')")
    public ResponseEntity<Void> createModerator(
            @AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody ModeratorCreateDTO dto) {
        adminFacade.createModerator(userPrincipal.getUser(), dto, RoleEnum.MODERATOR);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/regional-moderators")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserDTO>> getRegionalModerators(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return new ResponseEntity<>(adminFacade.getModerators(userPrincipal.getUser(), RoleEnum.REGIONAL_MODERATOR), HttpStatus.OK);
    }

    @GetMapping("/moderators")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'REGIONAL_MODERATOR')")
    public ResponseEntity<List<UserDTO>> getModerators(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return new ResponseEntity<>(adminFacade.getModerators(userPrincipal.getUser(), RoleEnum.MODERATOR), HttpStatus.OK);
    }

    @PatchMapping("/moderators/change-regions")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'REGIONAL_MODERATOR') and @securityFacade.checkIfUserHasEnoughAuthority(authentication, #dto.moderatorId)")
    public ResponseEntity<Void> changeModeratorRegions(@RequestBody ChangeModeratorRegionsDTO dto) {
        adminFacade.changeModeratorRegions(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/moderators/change-categories")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'REGIONAL_MODERATOR') and @securityFacade.checkIfUserHasEnoughAuthority(authentication, #dto.moderatorId)")
    public ResponseEntity<Void> changeModeratorCategories(@RequestBody ChangeModeratorCategoriesDTO dto) {
        adminFacade.changeModeratorCategories(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/moderators/{moderatorId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'REGIONAL_MODERATOR') and @securityFacade.checkIfUserHasEnoughAuthority(authentication, #moderatorId)")
    public ResponseEntity<Void> deleteModeratorById(@PathVariable Long moderatorId) {
        adminFacade.deleteModeratorById(moderatorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
