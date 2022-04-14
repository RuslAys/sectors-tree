package com.test.task.service;

import com.test.task.model.entity.Session;

public interface SessionService {
    Session createSession();
    Session getValidSession(Long sessionId);
}
