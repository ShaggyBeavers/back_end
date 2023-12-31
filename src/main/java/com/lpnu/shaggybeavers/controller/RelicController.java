package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.dto.RelicCatalogDTO;
import com.lpnu.shaggybeavers.dto.RelicDTO;
import com.lpnu.shaggybeavers.facade.RelicFacade;
import com.lpnu.shaggybeavers.filter.RelicFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @GetMapping("/page")
    public ResponseEntity<Page<RelicCatalogDTO>> getCatalog(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        Pageable pageable= PageRequest.of(page, size);
        return new ResponseEntity<>(relicFacade.getCatalog(pageable), HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<RelicDTO>> getRelicsByFilter(
            @RequestBody RelicFilter filter) {
        return new ResponseEntity<>(relicFacade.getRelicsByFilter(filter), HttpStatus.OK);
    }
}
