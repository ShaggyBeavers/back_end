package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.TechniqueCreateDTO;
import com.lpnu.shaggybeavers.dto.TechniqueDTO;
import com.lpnu.shaggybeavers.model.Technique;

import java.util.List;

public interface TechniqueMapper {

    Technique toTechnique(TechniqueCreateDTO techniqueCreateDTO);

    TechniqueDTO toTechniqueDTO(Technique technique);

    List<TechniqueDTO> toTechniqueDTOs(List<Technique> techniques);

}
