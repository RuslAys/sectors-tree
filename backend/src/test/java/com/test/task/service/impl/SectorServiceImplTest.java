package com.test.task.service.impl;

import com.test.task.model.entity.Sector;
import com.test.task.model.repository.SectorsRepository;
import com.test.task.service.SectorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;

class SectorServiceImplTest {

    private final SectorsRepository sectorsRepository = mock(SectorsRepository.class);
    private final SectorService sectorService = new SectorServiceImpl(sectorsRepository);

    @BeforeEach
    void setUp() {
        when(sectorsRepository.save(any())).then(Answers.RETURNS_MOCKS);
    }

    @Test
    void create() {
        Sector sector = sectorService.create("test", null);
        Assertions.assertEquals("test", sector.getName());
        Assertions.assertNull(sector.getParent());
    }

    @Test
    void update() {
        long sectorId = 1L;
        Sector sector = new Sector();
        sector.setId(sectorId);
        sector.setName("test");
        sector.setChildren(Set.of(new Sector(2L, "child", true, null, new HashSet<>())));
        when(sectorsRepository.findById(sectorId)).thenReturn(Optional.of(sector));
        Sector updatedSector = sectorService.update(sectorId, "test1", true, null);
        Assertions.assertEquals(sector.getId(), updatedSector.getId());
        Assertions.assertEquals("test1", updatedSector.getName());
        Assertions.assertTrue(updatedSector.getActive());
    }

    @Test
    void deactivate() {
        long sectorId = 1L;
        Sector sector = new Sector();
        sector.setId(sectorId);
        sector.setName("test");
        sector.setChildren(Set.of(new Sector(2L, "child", true, null, new HashSet<>())));
        when(sectorsRepository.findById(sectorId)).thenReturn(Optional.of(sector));
        sectorService.deactivate(sectorId);
        Assertions.assertFalse(sector.getActive());
        Assertions.assertFalse(sector.getChildren().stream().findFirst().get().getActive());
    }
}