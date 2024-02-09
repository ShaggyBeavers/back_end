package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.dto.*;
import com.lpnu.shaggybeavers.facade.UserFacade;
import com.lpnu.shaggybeavers.security.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("/current-profile")
    public ResponseEntity<UserProfileDTO> getProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return new ResponseEntity<>(userFacade.getProfile(userPrincipal.getId()), HttpStatus.OK);
    }

    @PutMapping("/edit/{userId}")
    public ResponseEntity<Void> editUser(
            @AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody @Valid UserEditDTO userEditDTO) {
        userFacade.editUser(userPrincipal.getId(), userEditDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/password/request-reset")
    public ResponseEntity<Void> requestResetPassword(@RequestBody @Valid EmailDTO emailDTO) {
        userFacade.requestResetPassword(emailDTO.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/password/reset")
    public ResponseEntity<Void> resetPassword(@RequestBody @Valid ResetPasswordDTO resetPasswordDTO) {
        userFacade.resetPassword(resetPasswordDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/password/change")
    public ResponseEntity<Void> changePassword(
            @AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody @Valid ChangePasswordDTO dto) {
        userFacade.changePassword(userPrincipal.getId(), dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<String> getEmail(@RequestParam Long userId){
        return new ResponseEntity<>(userFacade.getEmail(userId), HttpStatus.OK);
    }

    @PostMapping("/ban-unban/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'REGIONAL_MODERATOR', 'MODERATOR') and @securityFacade.checkIfUserHasEnoughAuthority(authentication, #userId)")
    public ResponseEntity<Void> banUnban(@PathVariable Long userId) {
        userFacade.banUnban(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
