package com.lpnu.shaggybeavers.factory;

import com.lpnu.shaggybeavers.dto.RelicCategoryCreateDTO;
import com.lpnu.shaggybeavers.mapper.EntityMapper;
import com.lpnu.shaggybeavers.model.Category;
import com.lpnu.shaggybeavers.model.Relic;
import com.lpnu.shaggybeavers.model.RelicCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RelicCategoryFactory {

    private final EntityMapper entityMapper;

}
