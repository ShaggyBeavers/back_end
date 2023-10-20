package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
