package com.lpnu.shaggybeavers.factory;

import com.lpnu.shaggybeavers.mapper.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PropertyFactory {

    private final EntityMapper entityMapper;

}
