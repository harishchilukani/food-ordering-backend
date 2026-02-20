package com.example.login.demo.service;

import com.example.login.demo.dto.UserAdressDto;
import com.example.login.demo.entity.UserAdress;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserAdressService {
    void saveUserAdress(Long userId, UserAdress userAdress);
    List<UserAdressDto>getUserAdress(Long userId);
}
