package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.RelicProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelicPropertyRepository extends JpaRepository<RelicProperty, Long> {
}
