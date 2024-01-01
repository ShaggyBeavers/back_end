package com.lpnu.shaggybeavers.dto;

import com.lpnu.shaggybeavers.domain.RelicStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class RelicDTO {

    private Long id;

    private String name;

    private LocalDate creationDate;

    private String creationPlaceName;

    private String imageUrl;

    private String museumName;

    private String regionName;

    private Integer quantity;

    private String collection;

    private String comment;

    private RelicStatus status;

    private List<RelicPropertyDTO> relicPropertyDTOs;

    private RelicInfoDTO relicInfoDTO;

    private RecoveredRelicInfoDTO recoveredRelicInfoDTO;

    private LostRelicInfoDTO lostRelicInfoDTO;

}
