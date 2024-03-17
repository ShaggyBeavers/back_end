package com.lpnu.shaggybeavers.factory;

import com.lpnu.shaggybeavers.dto.TechniqueCreateDTO;
import com.lpnu.shaggybeavers.dto.TechniqueDTO;
import com.lpnu.shaggybeavers.mapper.EntityMapper;
import com.lpnu.shaggybeavers.model.Technique;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TechniqueFactory {

    private final EntityMapper entityMapper;

    public Technique toTechnique(TechniqueCreateDTO techniqueCreateDTO) {
        return entityMapper.toTechnique(techniqueCreateDTO);
    }

    public TechniqueDTO toTechniqueDTO(Technique technique) {
        return entityMapper.toTechniqueDTO(technique);
    }

    public List<TechniqueDTO> toTechniqueDTOs(List<Technique> techniques) {
        return entityMapper.toTechniqueDTOs(techniques);
    }
}
