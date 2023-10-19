package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.Relic;
import com.lpnu.shaggybeavers.repository.RelicRepository;
import com.lpnu.shaggybeavers.service.RelicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RelicServiceImpl extends CRUDServiceImpl<Relic, Long> implements RelicService {

    private final RelicRepository relicRepository;

    @Override
    protected JpaRepository<Relic, Long> getRepository () {
        return this.relicRepository;
    }
}
