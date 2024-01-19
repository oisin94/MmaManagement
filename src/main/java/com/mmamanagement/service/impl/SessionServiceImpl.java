package com.mmamanagement.service.impl;

import com.mmamanagement.dto.SessionDto;
import com.mmamanagement.entity.Session;
import com.mmamanagement.entity.User;
import com.mmamanagement.mapper.SessionMapper;
import com.mmamanagement.repository.SessionRepository;
import com.mmamanagement.repository.UserRepository;
import com.mmamanagement.service.SessionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionService {

    private SessionRepository sessionRepository;

    private UserRepository userRepository;

    public SessionServiceImpl(SessionRepository sessionRepository,
                              UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<SessionDto> findAllSessions() {
        List<Session> sessions = sessionRepository.findAll();
        return sessions.stream().map(SessionMapper :: mapToSessionDto)
                .collect(Collectors.toList());
    }


    public void createSession(SessionDto sessionDto) {
        Set<User> users = sessionDto.getUserIds() != null
                ? sessionDto.getUserIds().stream()
                .map(userId -> userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId)))
                .collect(Collectors.toSet())
                : new HashSet<>();

        Session session = SessionMapper.mapToSession(sessionDto);
        session.setUsers(users);
        sessionRepository.save(session);
    }

    @Override
    public List<SessionDto> findSessionsBetween(LocalDateTime startOfWeek, LocalDateTime endOfWeek) {
        List<Session> sessions = sessionRepository.findSessionsBetween(startOfWeek, endOfWeek);
        return sessions.stream().map(SessionMapper :: mapToSessionDto)
                .collect(Collectors.toList());
    }

    @Override
    public SessionDto findSessionByUrl(String sessionUrl) {
        Session session = sessionRepository.findByUrl(sessionUrl).get();
        return SessionMapper.mapToSessionDto(session);
    }

    @Override
    public void deleteSession(Long sessionId) {
        sessionRepository.deleteById(sessionId);
    }

    @Override
    public void updateSession(SessionDto sessionDto) {
        Session session = SessionMapper.mapToSession(sessionDto);
        sessionRepository.save(session);
    }

    @Override
    public SessionDto findSessionById(Long sessionId) {
        Session session = sessionRepository.findById(sessionId).get();
        return SessionMapper.mapToSessionDto(session);
    }

    @Override
    public List<SessionDto> searchSessions(String query) {
        List<Session> sessions = sessionRepository.searchSessions(query);
        return sessions.stream()
                .map(SessionMapper :: mapToSessionDto)
                .collect(Collectors.toList());
    }



}
