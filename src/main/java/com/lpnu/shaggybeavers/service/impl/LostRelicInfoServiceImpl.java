package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.model.LostRelicInfo;
import com.lpnu.shaggybeavers.repository.LostRelicInfoRepository;
import com.lpnu.shaggybeavers.service.LostRelicInfoService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class LostRelicInfoServiceImpl extends CRUDServiceImpl<LostRelicInfo, Long> implements LostRelicInfoService {

    private final LostRelicInfoRepository lostRelicInfoRepository;

    @Lazy
    public LostRelicInfoServiceImpl(LostRelicInfoRepository lostRelicInfoRepository) {
        this.lostRelicInfoRepository = lostRelicInfoRepository;
    }

    @Override
    protected JpaRepository<LostRelicInfo, Long> getRepository() {
        return this.lostRelicInfoRepository;
    }

    @Override
    public boolean existsByMuseumId(Long museumId) {
        return lostRelicInfoRepository.existsByMuseumId(museumId);
    }
}

