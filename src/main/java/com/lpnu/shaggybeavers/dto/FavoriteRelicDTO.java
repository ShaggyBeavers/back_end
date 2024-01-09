package com.lpnu.shaggybeavers.dto;

import com.lpnu.shaggybeavers.domain.RelicStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class FavoriteRelicDTO {

    private String name;

    private RelicStatus status;

    private LocalDate lossTime;

    private List<CategoryDTO> categories;

    private String imageUrl;

}
