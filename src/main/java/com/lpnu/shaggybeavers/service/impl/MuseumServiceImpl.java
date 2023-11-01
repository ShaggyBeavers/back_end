package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.Museum;
import com.lpnu.shaggybeavers.repository.MuseumRepository;
import com.lpnu.shaggybeavers.service.MuseumService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MuseumServiceImpl extends CRUDServiceImpl<Museum, Long> implements MuseumService {

    private final MuseumRepository museumRepository;

    @Override
    protected JpaRepository<Museum, Long> getRepository() {
        return this.museumRepository;
    }
}
