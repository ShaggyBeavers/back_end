package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.HistoricalPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricalPeriodRepository extends JpaRepository<HistoricalPeriod, Long> {
}
