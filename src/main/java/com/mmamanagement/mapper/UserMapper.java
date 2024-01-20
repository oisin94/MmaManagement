package com.mmamanagement.mapper;

import com.mmamanagement.dto.SessionDto;
import com.mmamanagement.dto.UserDto;
import com.mmamanagement.entity.Session;
import com.mmamanagement.entity.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto mapToUserDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .beltRank(user.getBeltRank())
                .sessionsAttended(user.getSessionsAttended())
                .sessionIds(user.getSessions().stream()
                        .map((session) -> session.getId())
                        .collect(Collectors.toSet()))
                .build();
    }

    public static User mapToUser(UserDto userDto, Set<Session> sessions) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .beltRank(userDto.getBeltRank())
                .sessionsAttended(userDto.getSessionsAttended())
                .sessions(sessions)
                .build();
    }
}
