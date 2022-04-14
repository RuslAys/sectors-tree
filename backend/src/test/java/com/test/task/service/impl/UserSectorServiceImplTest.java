package com.test.task.service.impl;

import com.test.task.config.SessionConfig;
import com.test.task.model.entity.Session;
import com.test.task.model.repository.SessionRepository;
import com.test.task.model.repository.UserSectorRepository;
import com.test.task.service.SectorService;
import com.test.task.service.SessionService;
import com.test.task.service.UserSectorService;
import com.test.task.service.exception.SessionValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserSectorServiceImplTest {

    private final SessionRepository sessionRepository = mock(SessionRepository.class);
    private final SessionService sessionService = new SessionServiceImpl(new SessionConfig(), sessionRepository);
    private final SectorService sectorService = mock(SectorService.class);
    private final UserSectorRepository userSectorRepository = mock(UserSectorRepository.class);
    private final UserSectorService userSectorService = new UserSectorServiceImpl(sessionService, sectorService, userSectorRepository);


    @Test
    void createUserSector() {
        when(sessionRepository.findById(any())).thenReturn(Optional.of(new Session(1L, LocalDateTime.now(), LocalDateTime.now().plus(30, ChronoUnit.SECONDS))));
        when(sectorService.getAllActive(any())).thenReturn(Collections.emptyList());
        when(userSectorRepository.save(any())).thenAnswer(Answers.RETURNS_MOCKS);
        userSectorService.createUserSector(1L, "test", true, Collections.emptySet());
        Mockito.verify(userSectorRepository, times(1)).save(any());
    }

    @Test
    void createUserSector_nullSessionId() {
        when(sessionRepository.findById(any())).thenReturn(Optional.of(new Session(1L, LocalDateTime.now(), LocalDateTime.now().plus(30, ChronoUnit.SECONDS))));
        Assertions.assertThrows(SessionValidationException.class, () -> userSectorService.createUserSector(null, "test", true, new HashSet<>()));
    }

    @Test()
    void createUserSector_sessionExpired() {
        when(sessionRepository.findById(any())).thenReturn(Optional.of(
                new Session(1L, LocalDateTime.now().minus(1, ChronoUnit.MONTHS), LocalDateTime.now().minus(1, ChronoUnit.MONTHS))));
        Assertions.assertThrows(SessionValidationException.class, () -> userSectorService.createUserSector(null, "test", true, new HashSet<>()));
    }
}