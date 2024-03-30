package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.model.Relic;
import com.lpnu.shaggybeavers.model.RelicFile;
import com.lpnu.shaggybeavers.service.RelicFileService;
import com.lpnu.shaggybeavers.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RelicFileFacade {

    private final RelicFileService relicFileService;

    @Transactional
    public void createRelicFile(Relic relic, File file) {
        RelicFile relicFile = new RelicFile();
        relicFile.setRelic(relic);
        relicFile.setExtension(FileUtil.getExtension(file));
        relicFileService.uploadFile(relicFile, file);
        relicFileService.save(relicFile);
    }

    @Transactional(readOnly = true)
    public List<RelicFile> findAllByRelicId(Long relicId) {
        return relicFileService.findAllByRelicId(relicId);
    }

    @Transactional(readOnly = true)
    public Resource downloadFile(RelicFile relicFile) {
        return relicFileService.downloadFile(relicFile);
    }

    @Transactional
    public void deleteAllByRelicId(Long relicId) {
        List<RelicFile> relicFiles = relicFileService.findAllByRelicId(relicId);
        relicFiles.forEach(relicFile -> {
            relicFileService.deleteFile(relicFile);
            relicFileService.delete(relicFile);
        });
    }

}
