package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.RecoveredRelicInfo;
import com.lpnu.shaggybeavers.repository.RecoveredRelicInfoRepository;
import com.lpnu.shaggybeavers.service.RecoveredRelicInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecoveredRelicInfoServiceImpl extends CRUDServiceImpl<RecoveredRelicInfo, Long> implements RecoveredRelicInfoService {

    private final RecoveredRelicInfoRepository repository;

    @Override
    protected JpaRepository<RecoveredRelicInfo, Long> getRepository() {
        return this.repository;
    }
}
