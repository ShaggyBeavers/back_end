package com.lpnu.shaggybeavers.factory;

import com.lpnu.shaggybeavers.dto.RelicPropertyCreateDTO;
import com.lpnu.shaggybeavers.mapper.EntityMapper;
import com.lpnu.shaggybeavers.model.Property;
import com.lpnu.shaggybeavers.model.Relic;
import com.lpnu.shaggybeavers.model.RelicProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RelicPropertyFactory {

    private final EntityMapper entityMapper;

}
