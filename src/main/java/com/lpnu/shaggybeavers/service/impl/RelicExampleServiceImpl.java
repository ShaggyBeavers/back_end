package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.RelicExample;
import com.lpnu.shaggybeavers.repository.RelicExampleRepository;
import com.lpnu.shaggybeavers.service.RelicExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RelicExampleServiceImpl extends CRUDServiceImpl<RelicExample,Long> implements RelicExampleService {

    private final RelicExampleRepository repository;

    @Override
    protected JpaRepository<RelicExample, Long> getRepository () {
        return this.repository;
    }
}
