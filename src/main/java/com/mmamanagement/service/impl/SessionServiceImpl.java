package com.mmamanagement.service.impl;

import com.mmamanagement.dto.SessionDto;
import com.mmamanagement.entity.Session;
import com.mmamanagement.mapper.SessionMapper;
import com.mmamanagement.repository.SessionRepository;
import com.mmamanagement.service.SessionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionService {

    private SessionRepository sessionRepository;

    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public List<SessionDto> findAllSessions() {
        List<Session> sessions = sessionRepository.findAll();
        return sessions.stream().map(SessionMapper :: mapToSessionDto)
                .collect(Collectors.toList());
    }


    @Override
    public void createSession(SessionDto sessionDto) {
        Session session = SessionMapper.mapToSession(sessionDto);
        sessionRepository.save(session);
    }

    @Override
    public List<SessionDto> findSessionsBetween(LocalDateTime startOfWeek, LocalDateTime endOfWeek) {
        List<Session> sessions = sessionRepository.findSessionsBetween(startOfWeek, endOfWeek);
        return sessions.stream().map(SessionMapper :: mapToSessionDto)
                .collect(Collectors.toList());
    }


}