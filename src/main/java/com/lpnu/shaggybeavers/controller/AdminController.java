package com.lpnu.shaggybeavers.controller;

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

    @GetMapping("/moderators")
    public ResponseEntity<List<UserDTO>> getModerators() {
        return new ResponseEntity<>(adminFacade.getModerators(), HttpStatus.OK);
    }

    @DeleteMapping("/moderators/{moderatorId}")
    public ResponseEntity<Void> deleteModeratorById(@PathVariable Long moderatorId) {
        adminFacade.deleteModeratorById(moderatorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
