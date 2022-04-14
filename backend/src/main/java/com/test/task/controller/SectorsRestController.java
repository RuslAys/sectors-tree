package com.test.task.controller;

import com.test.task.controller.dto.SectorDto;
import com.test.task.model.entity.Sector;
import com.test.task.service.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sectors")
@RequiredArgsConstructor
public class SectorsRestController {

    private final SectorService sectorService;

    @GetMapping
    public List<SectorDto> getRoots() {
        List<SectorDto> roots = sectorService.getRoots().stream().map(this::toSectorDto).collect(Collectors.toList());
        if (roots.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Root sector does not exist");
        }
        return roots;
    }

    @PostMapping
    public SectorDto create(@RequestBody SectorDto sectorDto) {
        return toSectorDto(sectorService.create(sectorDto.getName(), sectorDto.getParent().getId()));
    }

    @PutMapping("/{id}")
    public SectorDto update(@PathVariable("id") Long id, @RequestBody SectorDto sectorDto) {
        return toSectorDto(sectorService.update(id, sectorDto.getName(), Boolean.TRUE.equals(sectorDto.getActive()), sectorDto.getParent().getId()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        sectorService.deactivate(id);
    }


    private SectorDto toSectorDto(Sector sector) {
        if (sector == null || !Boolean.TRUE.equals(sector.getActive())) {
            return null;
        }
        SectorDto result = new SectorDto();
        result.setId(sector.getId());
        result.setName(sector.getName());
        List<SectorDto> children = sector.getChildren().stream().map(c -> {
            SectorDto child = toSectorDto(c);
            if (child != null) {
                child.setParent(result);
            }
            return child;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        result.setChildren(children);
        return result;
    }

}
