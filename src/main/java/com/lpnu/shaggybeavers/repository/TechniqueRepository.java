package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.Technique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechniqueRepository extends JpaRepository<Technique, Long> {
}
