package com.example.login.demo.service;

import com.example.login.demo.dto.UserDto;
import com.example.login.demo.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    String signUp(User user);
    String login(String username, String password);
    UserDto gettingDetails(Long userId);
}
