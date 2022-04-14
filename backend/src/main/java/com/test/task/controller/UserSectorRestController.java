package com.test.task.controller;

import com.test.task.controller.dto.UserSectorDto;
import com.test.task.model.entity.Sector;
import com.test.task.model.entity.UserSector;
import com.test.task.service.UserSectorService;
import com.test.task.service.exception.SessionValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user_sectors")
@RequiredArgsConstructor
public class UserSectorRestController {

    private final UserSectorService userSectorService;

    @PostMapping(value = "/{sessionId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserSectorDto createNew(@PathVariable("sessionId") Long sessionId, @RequestBody UserSectorDto userSectorDto) {
        UserSector userSector;
        try {
            userSector = userSectorService.createUserSector(sessionId, userSectorDto.getUsername(), userSectorDto.getAgreedToTerms(), userSectorDto.getSectorIds());
        } catch (SessionValidationException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
        return toUserSectorDto(userSector);
    }

    @PutMapping("/{sessionId}")
    @ResponseBody
    public UserSectorDto update(@PathVariable("sessionId") Long sessionId, @RequestBody UserSectorDto userSectorDto) {
        UserSector userSector;
        try {
            userSector = userSectorService.updateUserSector(sessionId, userSectorDto.getId(), userSectorDto.getUsername(), userSectorDto.getAgreedToTerms(), userSectorDto.getSectorIds());
        } catch (SessionValidationException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return toUserSectorDto(userSector);
    }

    private UserSectorDto toUserSectorDto(UserSector userSector) {
        UserSectorDto userSectorDto = new UserSectorDto();
        userSectorDto.setId(userSector.getId());
        userSectorDto.setUsername(userSector.getUsername());
        userSectorDto.setAgreedToTerms(userSector.getAgreedToTerms());
        userSectorDto.setSectorIds(userSector.getSectors().stream().map(Sector::getId).collect(Collectors.toSet()));
        return userSectorDto;
    }
}
