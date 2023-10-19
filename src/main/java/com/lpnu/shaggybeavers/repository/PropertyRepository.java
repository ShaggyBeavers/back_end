package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
}
