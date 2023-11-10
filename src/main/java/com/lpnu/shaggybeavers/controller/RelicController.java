package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.facade.RelicFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

@RestController
@RequestMapping(value = "/api/relics")
@RequiredArgsConstructor
public class RelicController {

    private final RelicFacade relicFacade;

    @PostMapping("/upload")
    public ResponseEntity<URL> uploadFile(
            @RequestParam(value = "File") MultipartFile multipartFile, @RequestParam(value = "Relic ID") Long relicId) throws IOException {
        return new ResponseEntity<>(relicFacade.uploadFile(multipartFile, relicId), HttpStatus.CREATED);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<URL> downloadFile(
            @PathVariable(value = "fileName") String fileName) {
        return new ResponseEntity<>(relicFacade.downloadFile(fileName), HttpStatus.OK);
    }
}
