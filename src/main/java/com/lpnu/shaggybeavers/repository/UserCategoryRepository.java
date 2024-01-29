package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCategoryRepository extends JpaRepository<UserCategory,Long> {

    Optional<UserCategory> findByUserIdAndCategoryId(Long userId, Long categoryId);

    List<UserCategory> findAllByUserId(Long userId);

}
