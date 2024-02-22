package com.lpnu.shaggybeavers.factory;

import com.lpnu.shaggybeavers.dto.MuseumCreateDTO;
import com.lpnu.shaggybeavers.mapper.EntityMapper;
import com.lpnu.shaggybeavers.model.Museum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MuseumFactory {

    private final EntityMapper entityMapper;

    public Museum toMuseum(MuseumCreateDTO museumCreateDTO) {
        return entityMapper.toMuseum(museumCreateDTO);
    }
}
