package com.lpnu.shaggybeavers.dto;

import com.lpnu.shaggybeavers.model.Category;
import com.lpnu.shaggybeavers.model.Property;
import com.lpnu.shaggybeavers.model.Relic;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelicPropertyCreateDTO {

    private Property property;

    private Relic relic;

    private String value;
}
