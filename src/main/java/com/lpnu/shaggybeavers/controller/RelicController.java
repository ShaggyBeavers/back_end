package com.lpnu.shaggybeavers.controller;

import com.lpnu.shaggybeavers.domain.RelicStatus;
import com.lpnu.shaggybeavers.dto.FavoriteRelicDTO;
import com.lpnu.shaggybeavers.dto.RelicCatalogDTO;
import com.lpnu.shaggybeavers.dto.RelicCreateEditDTO;
import com.lpnu.shaggybeavers.dto.RelicDTO;
import com.lpnu.shaggybeavers.facade.RelicFacade;
import com.lpnu.shaggybeavers.filter.RelicFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
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


    @PutMapping("/edit/{relicId}")
    public void editRelic(
            @PathVariable(value = "relicId") Long relicId, @RequestBody RelicCreateEditDTO relicCreateEditDTO){
        relicFacade.editRelic(relicId, relicCreateEditDTO);
    }

    @PostMapping("/create")
    public void createRelic(
           @RequestBody RelicCreateEditDTO relicCreateEditDTO){
        relicFacade.createRelic(relicCreateEditDTO);
    }

    @PostMapping("/change-favorite")
    public ResponseEntity<Void> changeFavoriteRelic() {
        relicFacade.changeFavoriteRelic();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/favorite")
    public ResponseEntity<FavoriteRelicDTO> getFavoriteRelic() {
        return new ResponseEntity<>(relicFacade.getFavoriteRelic(), HttpStatus.OK);
    }

    @GetMapping("/count-by-statuses")
    public ResponseEntity<Long> countByStatuses(
            @RequestParam List<RelicStatus> statuses) {
        return new ResponseEntity<>(relicFacade.countByStatuses(statuses), HttpStatus.OK);
    }

    @PostMapping("/file/upload/{relicId}")
    public ResponseEntity<Void> uploadFile(
            @PathVariable Long relicId, @RequestParam(value = "file") MultipartFile multipartFile) {
        relicFacade.uploadFile(relicId, multipartFile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/file/download/{relicId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long relicId) {
        return new ResponseEntity<>(relicFacade.downloadFile(relicId), HttpStatus.OK);
    }

    @DeleteMapping("/file/delete/{relicId}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long relicId) {
        relicFacade.deleteFile(relicId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/file/update/{relicId}")
    public ResponseEntity<Void> updateFile(
            @PathVariable Long relicId, @RequestParam(value = "file") MultipartFile multipartFile) {
        relicFacade.updateFile(relicId, multipartFile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<RelicCatalogDTO>> getRelicsByName(@RequestParam String relicName,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        Pageable pageable= PageRequest.of(page, size);
        return new ResponseEntity<>(relicFacade.getRelicsByName(relicName, pageable), HttpStatus.OK);
    }

}
