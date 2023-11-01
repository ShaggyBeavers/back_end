package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.Museum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuseumRepository extends JpaRepository<Museum, Long> {
}
