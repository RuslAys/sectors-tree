package com.test.task.model.repository;

import com.test.task.model.entity.Sector;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SectorsRepository extends JpaRepository<Sector, Long> {
    @EntityGraph("Sector.children")
    List<Sector> getAllByActiveIsTrueAndParentIsNull();

    @EntityGraph("Sector.children")
    List<Sector> findAllByActiveIsTrueAndIdIn(Set<Long> ids);

}
