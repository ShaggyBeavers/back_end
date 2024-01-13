package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.ResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResetTokenRepository extends JpaRepository<ResetToken, Long> {

    ResetToken findByUserId(Long userId);

    Optional<ResetToken> findByToken(String token);

}
