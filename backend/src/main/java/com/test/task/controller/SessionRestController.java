package com.test.task.controller;

import com.test.task.controller.dto.SessionDto;
import com.test.task.model.entity.Session;
import com.test.task.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionRestController {

    private final SessionService sessionService;

    @GetMapping("/new")
    public SessionDto getNewSession() {
        return toSessionDto(sessionService.createSession());
    }

    private SessionDto toSessionDto(Session session) {
        SessionDto dto = new SessionDto();
        dto.setId(session.getId());
        dto.setStart(OffsetDateTime.of(session.getStartTime(), ZoneOffset.UTC));
        dto.setEnd(OffsetDateTime.of(session.getEndTime(), ZoneOffset.UTC));
        return dto;
    }
}
