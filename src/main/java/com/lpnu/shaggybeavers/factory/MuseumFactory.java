package com.lpnu.shaggybeavers.factory;

import com.lpnu.shaggybeavers.dto.MuseumCreateDTO;
import com.lpnu.shaggybeavers.dto.MuseumDTO;
import com.lpnu.shaggybeavers.mapper.EntityMapper;
import com.lpnu.shaggybeavers.model.Museum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MuseumFactory {

    private final EntityMapper entityMapper;

    public Museum toMuseum(MuseumCreateDTO museumCreateDTO) {
        return entityMapper.toMuseum(museumCreateDTO);
    }

    public MuseumDTO toMuseumDTO(Museum museum) {
        return entityMapper.toMuseumDTO(museum);
    }

    public List<MuseumDTO> toMuseumDTOs(List<Museum> museums) {
        return entityMapper.toMuseumDTOs(museums);
    }

}
