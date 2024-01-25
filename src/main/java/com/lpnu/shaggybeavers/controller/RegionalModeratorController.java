package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.dto.ModeratorCreateDTO;
import com.lpnu.shaggybeavers.facade.RegionalModeratorFacade;
import com.lpnu.shaggybeavers.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/regional-moderator")
@RequiredArgsConstructor
public class RegionalModeratorController {

    private final RegionalModeratorFacade regionalModeratorFacade;

    @PostMapping("/moderators")
    public ResponseEntity<Void> createModerator(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody ModeratorCreateDTO dto) {
        regionalModeratorFacade.createModerator(dto, userPrincipal.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
