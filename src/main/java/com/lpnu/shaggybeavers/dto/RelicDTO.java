package com.lpnu.shaggybeavers.dto;

import com.lpnu.shaggybeavers.domain.RelicStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RelicDTO {

    private Long id;

    private String name;

    private String creationDate;

    private String creationPlaceName;

    private String museumName;

    private Integer quantity;

    private String collection;

    private String comment;

    private RelicStatus status;

    private String copyInformation;

    private String copyCreationTime;

    private String entryBookNumber;

    private String inventoryNumber;

    private String formerInventoryNumber;

    private String author;

    private List<RelicPropertyDTO> relicPropertyDTOs;

    private List<CategoryDTO> categoryDTOs;

    private RelicInfoDTO relicInfoDTO;

    private RecoveredRelicInfoDTO recoveredRelicInfoDTO;

    private LostRelicInfoDTO lostRelicInfoDTO;

    private RegionDTO regionDTO;

}
