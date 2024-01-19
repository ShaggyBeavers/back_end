package com.lpnu.shaggybeavers.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;

@MappedSuperclass
@Getter
@Setter
public class FileEntity <CONTENT_ID> {

    @ContentId
    private CONTENT_ID contentId;

    @ContentLength
    private Long contentLength;

}
