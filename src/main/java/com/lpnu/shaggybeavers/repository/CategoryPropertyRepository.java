package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.CategoryProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryPropertyRepository extends JpaRepository<CategoryProperty, Long> {
}
