package com.lpnu.shaggybeavers.mapper;

import com.lpnu.shaggybeavers.dto.MuseumCreateDTO;
import com.lpnu.shaggybeavers.model.Museum;

public interface MuseumMapper {

    Museum toMuseum(MuseumCreateDTO museumCreateDTO);
}
