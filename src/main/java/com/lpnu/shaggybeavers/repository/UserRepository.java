package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
