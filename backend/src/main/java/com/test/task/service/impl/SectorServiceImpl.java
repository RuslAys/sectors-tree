package com.test.task.service.impl;

import com.test.task.model.entity.Sector;
import com.test.task.model.repository.SectorsRepository;
import com.test.task.service.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SectorServiceImpl implements SectorService {

    private final SectorsRepository sectorsRepository;

    @Override
    public List<Sector> getRoots() {
        return sectorsRepository.getAllByActiveIsTrueAndParentIsNull();
    }

    @Override
    public List<Sector> getAllActive(Set<Long> sectorIds) {
        return sectorsRepository.findAllByActiveIsTrueAndIdIn(sectorIds);
    }

    @Override
    @Transactional
    public Sector create(String name, Long parentId) {
        Sector sector = new Sector();
        sector.setName(name);
        if (parentId != null) {
            Sector parent = sectorsRepository.findById(parentId).orElseThrow(() -> new IllegalArgumentException("Parent sector does not exist"));
            sector.setParent(parent);
        }
        sectorsRepository.save(sector);
        return sector;
    }

    @Override
    public Sector update(Long id, String name, boolean active, Long parentId) {
        Sector sector = sectorsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Sector does not exist"));
        sector.setName(name);
        if (parentId != null) {
            Sector parent = sectorsRepository.findById(parentId).orElseThrow(() -> new IllegalArgumentException("Parent sector does not exist"));
            sector.setParent(parent);
        } else {
            sector.setParent(null);
        }
        sectorsRepository.save(sector);
        return sector;
    }

    @Override
    public void deactivate(Long id) {
        Sector sector = sectorsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Sector does not exist"));
        sector.setActive(false);
        disableChildren(sector);
        sectorsRepository.save(sector);
    }

    private void disableChildren(Sector sector) {
        if (CollectionUtils.isEmpty(sector.getChildren())) {
            return;
        }
        sector.getChildren().forEach(c -> {
            c.setActive(false);
            disableChildren(c);
        });
    }
}
