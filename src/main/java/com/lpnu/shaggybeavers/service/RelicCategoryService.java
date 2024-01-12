package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.model.RelicCategory;

public interface RelicCategoryService extends CRUDService<RelicCategory, Long> {

    void create(Long relicId, Long categoryId);
}
