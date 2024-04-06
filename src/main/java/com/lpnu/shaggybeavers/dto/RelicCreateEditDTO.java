package com.lpnu.shaggybeavers.dto;

import com.lpnu.shaggybeavers.domain.RelicStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class RelicCreateEditDTO {

    private String objectType;

    private RelicStatus status;

    private String creationDate;

    private String author;

    private Long regionId;

    private String name;

    private Long creationPlaceId;

    private List<Long> reportIds = new ArrayList<>();

    private List<Long> relicCategoryIds = new ArrayList<>();

    private Long museumId;

    private Integer quantity;

    private String collection;

    private String comment;

    private String copyInformation;

    private String copyCreationTime;

    private String entryBookNumber;

    private String inventoryNumber;

    private String formerInventoryNumber;

    private List<Long> relicPropertyIds = new ArrayList<>();

    private List<String> propertyValues = new ArrayList<>();

    private String imageUrl;

    private RelicInfoCreateEditDTO relicInfoCreateEditDTO;

}
