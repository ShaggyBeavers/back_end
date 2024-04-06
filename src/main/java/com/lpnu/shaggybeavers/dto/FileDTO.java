package com.lpnu.shaggybeavers.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.Resource;

@Getter
@Setter
public class FileDTO {

    private Resource file;

    private String extension;

    private Long entityId;

}
