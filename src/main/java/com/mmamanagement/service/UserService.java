package com.mmamanagement.service;

import com.mmamanagement.dto.UserDto;

public interface UserService {


    void addUserToSession(String email, Long sessionId);

    UserDto getUserByEmail(String email);
}
