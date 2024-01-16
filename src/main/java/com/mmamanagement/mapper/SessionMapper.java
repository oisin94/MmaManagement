package com.mmamanagement.mapper;

import com.mmamanagement.dto.SessionDto;
import com.mmamanagement.entity.Session;

import java.util.stream.Collectors;

public class SessionMapper {

    public static SessionDto mapToSessionDto(Session session){
        return SessionDto.builder()
                .id(session.getId())
                .sessionName(session.getSessionName())
                .url(session.getUrl())
                .description(session.getDescription())
                .trainer(session.getTrainer())
                .sessionTime(session.getSessionTime())
                .beltRank(session.getBeltRank())
                // to stream through the comments and map each comment to a commentDto
//                .comments(session.getComments().stream()
//                        .map((comment) -> CommentMapper.mapToCommentDto(comment))
//                        .collect(Collectors.toSet()))
                .build();
    }

    // map Postdto to Post entity
    public static Session mapToSession(SessionDto sessionDto){
        return Session.builder()
                .id(sessionDto.getId())
                .sessionName(sessionDto.getSessionName())
                .url(sessionDto.getUrl())
                .description(sessionDto.getDescription())
                .sessionTime(sessionDto.getSessionTime())
                .trainer(sessionDto.getTrainer())
                .beltRank(sessionDto.getBeltRank())
                .build();
    }
}
