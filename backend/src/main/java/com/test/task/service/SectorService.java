package com.test.task.service;

import com.test.task.model.entity.Sector;

import java.util.List;
import java.util.Set;

public interface SectorService {
    List<Sector> getRoots();

    List<Sector> getAllActive(Set<Long> sectorIds);

    Sector create(String name, Long parentId);

    Sector update(Long id, String name, boolean active, Long parentId);

    void deactivate(Long id);
}
