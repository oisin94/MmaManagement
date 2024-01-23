package com.mmamanagement.service;

import com.mmamanagement.dto.RegistrationDto;
import com.mmamanagement.dto.UserDto;
import com.mmamanagement.entity.User;

public interface UserService {


    void addUserToSession(String email, Long sessionId);

    UserDto getUserByEmail(String email);

    User findByEmail(String email);


    void saveUser(RegistrationDto user);
}
