package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.RelicExample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelicExampleRepository extends JpaRepository<RelicExample,Long> {

    RelicExample findByEmail(String email);

}
