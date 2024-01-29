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
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminFacade adminFacade;

    @DeleteMapping("/moderators/{moderatorId}")
    public ResponseEntity<Void> deleteModeratorById(@PathVariable Long moderatorId) {
        adminFacade.deleteModeratorById(moderatorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/regional-moderators")
    public ResponseEntity<Void> createRegionalModerator(@RequestBody ModeratorCreateDTO dto) {
        adminFacade.createModerator(dto, RoleEnum.REGIONAL_MODERATOR);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/moderators")
    public ResponseEntity<Void> createModerator(@RequestBody ModeratorCreateDTO dto) {
        adminFacade.createModerator(dto, RoleEnum.MODERATOR);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/regional-moderators")
    public ResponseEntity<List<UserDTO>> getRegionalModerators() {
        return new ResponseEntity<>(adminFacade.getModerators(RoleEnum.REGIONAL_MODERATOR), HttpStatus.OK);
    }

    @GetMapping("/moderators")
    public ResponseEntity<List<UserDTO>> getModerators() {
        return new ResponseEntity<>(adminFacade.getModerators(RoleEnum.MODERATOR), HttpStatus.OK);
    }

}
