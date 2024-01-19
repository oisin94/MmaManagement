package com.mmamanagement.service.impl;

import com.mmamanagement.dto.UserDto;
import com.mmamanagement.entity.Session;
import com.mmamanagement.entity.User;
import com.mmamanagement.mapper.UserMapper;
import com.mmamanagement.repository.SessionRepository;
import com.mmamanagement.repository.UserRepository;
import com.mmamanagement.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private SessionRepository sessionRepository;

    public UserServiceImpl(UserRepository userRepository,
                           SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

//    public void createUser(UserDto userDto) {
//        User user = UserMapper.mapToUser(userDto);
//        userRepository.save(user);
//    }

//    public User findByUsername(String username) {
//        return userRepository.findByUsername(username).get();
//    }


    @Override
    public void addUserToSession(String email, Long sessionId) {
        User user = userRepository.findByEmail(email);
        Session session = sessionRepository.findById(sessionId).get();

        session.getUsers().add(user);
        sessionRepository.save(session);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return UserMapper.mapToUserDto(user);
    }


}
