package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.TechniqueCreateDTO;
import com.lpnu.shaggybeavers.model.Technique;

public interface TechniqueMapper {

    Technique toTechnique(TechniqueCreateDTO techniqueCreateDTO);

}
