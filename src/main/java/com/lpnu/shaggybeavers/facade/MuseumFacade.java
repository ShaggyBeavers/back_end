package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.MuseumFactory;
import com.lpnu.shaggybeavers.service.MuseumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MuseumFacade {

    private final MuseumFactory museumFactory;

    private final MuseumService museumService;

}
