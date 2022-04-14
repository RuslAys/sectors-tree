package com.test.task.model.repository;

import com.test.task.model.entity.UserSector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSectorRepository extends JpaRepository<UserSector, Long> {
}
