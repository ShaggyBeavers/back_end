package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.LostRelicInfo;
import com.lpnu.shaggybeavers.repository.LostRelicInfoRepository;
import com.lpnu.shaggybeavers.service.LostRelicInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LostRelicInfoServiceImpl extends CRUDServiceImpl<LostRelicInfo, Long> implements LostRelicInfoService {

    private final LostRelicInfoRepository lostRelicInfoRepository;

    @Override
    protected JpaRepository<LostRelicInfo, Long> getRepository() {
        return this.lostRelicInfoRepository;
    }
}

