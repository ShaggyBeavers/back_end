package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.model.RelicProperty;

public interface RelicPropertyService extends CRUDService<RelicProperty, Long> {

    void create(Long relicId, Long propertyId, String value);
}
