package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.RelicCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelicCategoryRepository extends JpaRepository<RelicCategory, Long> {
}
