package com.lpnu.shaggybeavers.dto;

import com.lpnu.shaggybeavers.domain.RelicStatus;
import com.lpnu.shaggybeavers.model.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class RelicCreateEditDTO {

    private String objectType;

    private RelicStatus status;

    private LocalDate creationDate;

    private String author;

    private Region region;

    private String name;

    private Region creationPlace;

    private List<Report> reports = new ArrayList<>();

    private List<RelicCategory> relicCategories = new ArrayList<>();

    private Museum museum;

    private Integer quantity;

    private String collection;

    private String comment;

    private String copyInformation;

    private LocalDate copyCreationTime;

    private String entryBookNumber;

    private Integer inventoryNumber;

    private Integer formerInventoryNumber;

    private List<RelicProperty> relicProperties = new ArrayList<>();

    private String imageUrl;

    private RelicInfoCreateEditDTO relicInfoCreateEditDTO;

}
