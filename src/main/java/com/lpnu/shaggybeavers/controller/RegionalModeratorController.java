package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.domain.RoleEnum;
import com.lpnu.shaggybeavers.dto.ModeratorCreateDTO;
import com.lpnu.shaggybeavers.dto.UserDTO;
import com.lpnu.shaggybeavers.facade.AdminFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regional-moderator")
@RequiredArgsConstructor
public class RegionalModeratorController {

    private final AdminFacade adminFacade;

    @PostMapping("/moderators")
    public ResponseEntity<Void> createModerator(@RequestBody ModeratorCreateDTO dto) {
        adminFacade.createModerator(dto, RoleEnum.MODERATOR);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/moderators")
    public ResponseEntity<List<UserDTO>> getModerators() {
        return new ResponseEntity<>(adminFacade.getModerators(RoleEnum.MODERATOR), HttpStatus.OK);
    }

}
