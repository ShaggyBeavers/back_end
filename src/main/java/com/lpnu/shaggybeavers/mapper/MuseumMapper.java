package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.MuseumCreateDTO;
import com.lpnu.shaggybeavers.dto.MuseumDTO;
import com.lpnu.shaggybeavers.model.Museum;

import java.util.List;

public interface MuseumMapper {

    Museum toMuseum(MuseumCreateDTO museumCreateDTO);

    MuseumDTO toMuseumDTO(Museum museum);

    List<MuseumDTO> toMuseumDTOs(List<Museum> museums);

}
