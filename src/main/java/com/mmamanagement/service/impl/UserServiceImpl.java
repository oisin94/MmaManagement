package com.mmamanagement.service.impl;

import com.mmamanagement.dto.RegistrationDto;
import com.mmamanagement.dto.UserDto;
import com.mmamanagement.entity.Role;
import com.mmamanagement.entity.Session;
import com.mmamanagement.entity.User;
import com.mmamanagement.mapper.UserMapper;
import com.mmamanagement.repository.SessionRepository;
import com.mmamanagement.repository.UserRepository;
import com.mmamanagement.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.mmamanagement.repository.RoleRepository;


import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private SessionRepository sessionRepository;

    public UserServiceImpl(UserRepository userRepository,
                           SessionRepository sessionRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }



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

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }



    @Override
    public void saveUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setName(registrationDto.getFirstName() + " " + registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setBeltRank("White");
        user.setSessionsAttended(0);
        user.setSessionsToNextRank(10);
        // use spring security to encrypt the password
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

}
