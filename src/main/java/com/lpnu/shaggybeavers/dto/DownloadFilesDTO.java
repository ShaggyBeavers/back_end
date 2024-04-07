package com.lpnu.shaggybeavers.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DownloadFilesDTO {

    List<Long> entityIds;

}
