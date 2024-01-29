package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.model.UserCategory;

import java.util.List;
import java.util.Optional;

public interface UserCategoryService extends CRUDService<UserCategory,Long> {

    Optional<UserCategory> findByUserIdAndCategoryId(Long userId, Long categoryId);

    List<UserCategory> findAllByUserId(Long userId);

}
