package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.model.LostRelicInfo;

public interface LostRelicInfoService extends CRUDService<LostRelicInfo, Long> {
    boolean existsByMuseumId(Long museumId);
}
