package com.test.task.service.impl;

import com.test.task.model.entity.Session;
import com.test.task.model.entity.UserSector;
import com.test.task.model.repository.UserSectorRepository;
import com.test.task.service.SectorService;
import com.test.task.service.SessionService;
import com.test.task.service.UserSectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserSectorServiceImpl implements UserSectorService {

    private final SessionService sessionService;
    private final SectorService sectorService;
    private final UserSectorRepository userSectorRepository;

    @Override
    @Transactional
    public UserSector createUserSector(Long sessionId, String username, Boolean agreedToTerms, Set<Long> sectorIds) {
        Session validSession = sessionService.getValidSession(sessionId);
        UserSector userSector = new UserSector();
        userSector.setUsername(username);
        userSector.setAgreedToTerms(agreedToTerms);
        userSector.setSectors(new HashSet<>(sectorService.getAllActive(sectorIds)));
        userSector.setSession(validSession);
        return userSectorRepository.save(userSector);
    }

    @Override
    @Transactional
    public UserSector updateUserSector(Long sessionId, Long id, String userName, Boolean agreedToTerms, Set<Long> sectorIds) {
        Session validSession = sessionService.getValidSession(sessionId);
        UserSector userSector = userSectorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Can`t find records by id " + id));
        if (!validSession.equals(userSector.getSession())) {
            throw new IllegalArgumentException("Sessions are not equal");
        }
        userSector.setUsername(userName);
        userSector.setAgreedToTerms(agreedToTerms);
        userSector.setSectors(new HashSet<>(sectorService.getAllActive(sectorIds)));
        return userSectorRepository.save(userSector);
    }
}
