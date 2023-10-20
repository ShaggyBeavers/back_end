package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region,Long> {

}
