package com.mmamanagement.mapper;

import com.mmamanagement.dto.SessionDto;
import com.mmamanagement.entity.Session;
import com.mmamanagement.entity.User;

import java.util.Set;
import java.util.stream.Collectors;

public class SessionMapper {

    public static SessionDto mapToSessionDto(Session session){
        return SessionDto.builder()
                .id(session.getId())
                .sessionName(session.getSessionName())
                .url(session.getUrl())
                .maxCapacity(session.getMaxCapacity())
                .description(session.getDescription())
                .trainer(session.getTrainer())
                .sessionTime(session.getSessionTime())
                .beltRank(session.getBeltRank())
                .userIds(session.getUsers().stream()
                        .map((user) -> user.getId())
                        .collect(Collectors.toSet()))
                // to stream through the comments and map each comment to a commentDto
//                .comments(session.getComments().stream()
//                        .map((comment) -> CommentMapper.mapToCommentDto(comment))
//                        .collect(Collectors.toSet()))
                .build();
    }

    public static Session mapToSession(SessionDto sessionDto) {
        return Session.builder()
                .id(sessionDto.getId())
                .sessionName(sessionDto.getSessionName())
                .url(sessionDto.getUrl())
                .description(sessionDto.getDescription())
                .maxCapacity(sessionDto.getMaxCapacity())
                .sessionTime(sessionDto.getSessionTime())
                .trainer(sessionDto.getTrainer())
                .beltRank(sessionDto.getBeltRank())
                .build();
    }
}
