package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.RelicCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelicCategoryRepository extends JpaRepository<RelicCategory, Long> {

    List<RelicCategory> findAllByRelicId(Long relicId);

}
