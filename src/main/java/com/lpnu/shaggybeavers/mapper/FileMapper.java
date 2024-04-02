package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.FileDTO;
import org.springframework.core.io.Resource;

public interface FileMapper {

    default FileDTO toFileDTO(Resource file, String extension) {
        FileDTO fileDTO = new FileDTO();
        fileDTO.setFile(file);
        fileDTO.setExtension(extension);
        return fileDTO;
    }

}
