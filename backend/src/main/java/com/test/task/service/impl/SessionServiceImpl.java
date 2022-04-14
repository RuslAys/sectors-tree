package com.test.task.service.impl;

import com.test.task.config.SessionConfig;
import com.test.task.model.entity.Session;
import com.test.task.model.repository.SessionRepository;
import com.test.task.service.SessionService;
import com.test.task.service.exception.SessionValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionConfig sessionConfig;
    private final SessionRepository sessionRepository;

    @Override
    @Transactional
    public Session createSession() {
        Session session = new Session();
        var now = LocalDateTime.now();
        session.setStartTime(now);
        session.setEndTime(now.plus(sessionConfig.getDurationSec(), ChronoUnit.SECONDS));
        return sessionRepository.save(session);
    }

    @Override
    public Session getValidSession(Long sessionId) {
        if (sessionId == null) {
            throw new SessionValidationException("Session id required");
        }
        Session session = sessionRepository.findById(sessionId).orElseThrow(() -> new SessionValidationException("Session does not exist"));
        if (session.getEndTime().isBefore(LocalDateTime.now())) {
            throw new SessionValidationException("Session expired");
        }
        return session;
    }
}
