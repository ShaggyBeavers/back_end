package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.model.Relic;

import java.net.URL;

public interface RelicService extends CRUDService<Relic,Long> {
    void setImageUrl(URL url, Long relicId);
}
