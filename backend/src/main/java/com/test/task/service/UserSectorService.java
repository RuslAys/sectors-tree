package com.test.task.service;

import com.test.task.model.entity.UserSector;

import java.util.Set;

public interface UserSectorService {
    UserSector createUserSector(Long sessionId, String username, Boolean agreedToTerms, Set<Long> sectorIds);

    UserSector updateUserSector(Long sessionId, Long id, String userName, Boolean agreedToTerms, Set<Long> sectorIds);
}
