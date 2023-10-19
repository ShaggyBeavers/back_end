package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.Relic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelicRepository extends JpaRepository<Relic, Long> {
}
