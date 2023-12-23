package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.dto.UserDTO;
import com.lpnu.shaggybeavers.facade.UserFacade;
import com.lpnu.shaggybeavers.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("current-profile")
    public ResponseEntity<UserDTO> getProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return new ResponseEntity<>(userFacade.getProfile(userPrincipal.getId()), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam(value = "file") MultipartFile multipartFile, @RequestParam(value = "userId") Long userId) {
        return new ResponseEntity<>(userFacade.uploadFile(multipartFile, userId), HttpStatus.CREATED);
    }

    @GetMapping("/download/{relicId}")
    public ResponseEntity<String> downloadFile(
            @PathVariable(value = "relicId") Long userId) {
        return new ResponseEntity<>(userFacade.downloadFile(userId), HttpStatus.OK);
    }
}
