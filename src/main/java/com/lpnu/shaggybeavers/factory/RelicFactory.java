package com.lpnu.shaggybeavers.factory;

import com.lpnu.shaggybeavers.dto.RelicDTO;
import com.lpnu.shaggybeavers.mapper.EntityMapper;
import com.lpnu.shaggybeavers.model.Relic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RelicFactory {

    private final EntityMapper entityMapper;

    public RelicDTO toRelicDTO(Relic relic) {
        return entityMapper.toRelicDTO(relic);
    }

}
