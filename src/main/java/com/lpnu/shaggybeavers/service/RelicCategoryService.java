package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.model.RelicCategory;

import java.util.List;

public interface RelicCategoryService extends CRUDService<RelicCategory, Long> {

    void create(Long relicId, Long categoryId);

    List<RelicCategory> findAllByRelicId(Long relicId);

}
