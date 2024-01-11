package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.LostRelicInfoCreateEditDTO;
import com.lpnu.shaggybeavers.factory.LostRelicInfoFactory;
import com.lpnu.shaggybeavers.model.LostRelicInfo;
import com.lpnu.shaggybeavers.service.LostRelicInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LostRelicInfoFacade {

    private final LostRelicInfoFactory lostRelicInfoFactory;

    private final LostRelicInfoService lostRelicInfoService;

    public LostRelicInfo findById(Long id) { return lostRelicInfoService.findById(id); }

    public void save(LostRelicInfo lostRelicInfo) { lostRelicInfoService.save(lostRelicInfo); }

    public LostRelicInfo toLostRelicInfo(LostRelicInfoCreateEditDTO lostRelicInfoCreateEditDTO) {
        return lostRelicInfoFactory.toLostRelicInfo(lostRelicInfoCreateEditDTO);
    }

    public LostRelicInfo update(Long id, LostRelicInfoCreateEditDTO lostRelicInfoCreateEditDTO) {
        return lostRelicInfoFactory.update(lostRelicInfoService.findById(id), lostRelicInfoCreateEditDTO);
    }
}
