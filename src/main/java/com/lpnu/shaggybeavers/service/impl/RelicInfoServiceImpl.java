package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.RelicInfo;
import com.lpnu.shaggybeavers.repository.RelicInfoRepository;
import com.lpnu.shaggybeavers.service.RelicInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RelicInfoServiceImpl extends CRUDServiceImpl<RelicInfo, Long> implements RelicInfoService {

    private final RelicInfoRepository relicInfoRepository;

    @Override
    public JpaRepository<RelicInfo, Long> getRepository() {
        return this.relicInfoRepository;
    }
}
