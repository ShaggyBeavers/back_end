package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.RelicFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelicFileRepository extends JpaRepository<RelicFile, Long> {

    List<RelicFile> findAllByRelicId(Long relicId);

}
