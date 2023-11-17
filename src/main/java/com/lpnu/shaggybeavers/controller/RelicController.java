package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.dto.RelicDTO;
import com.lpnu.shaggybeavers.facade.RelicFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/relics")
@RequiredArgsConstructor
public class RelicController {

    private final RelicFacade relicFacade;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam(value = "file") MultipartFile multipartFile, @RequestParam(value = "relicId") Long relicId) {
        return new ResponseEntity<>(relicFacade.uploadFile(multipartFile, relicId), HttpStatus.CREATED);
    }

    @GetMapping("/download/{relicId}")
    public ResponseEntity<String> downloadFile(
            @PathVariable(value = "relicId") Long relicId) {
        return new ResponseEntity<>(relicFacade.downloadFile(relicId), HttpStatus.OK);
    }

    @GetMapping("/{relicId}")
    public ResponseEntity<RelicDTO> getRelicById(
            @PathVariable(value = "relicId") Long relicId) {
        return new ResponseEntity<>(relicFacade.getRelicById(relicId), HttpStatus.OK);
    }

}
