package com.example.login.demo.controller;

import com.example.login.demo.dto.UserAdressDto;
import com.example.login.demo.entity.UserAdress;
import com.example.login.demo.service.UserAdressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserAdressController {
    private final UserAdressService userAdressService;
    @PostMapping("addadressofuser/{userId}")
    public ResponseEntity<String> addUserAdress(@PathVariable Long userId, @RequestBody UserAdress userAdress)
    {
        userAdressService.saveUserAdress(userId, userAdress);
        return new ResponseEntity<>("useradress added successfully", HttpStatus.OK);
    }
    @GetMapping("getadressofuser/{userId}")
    public ResponseEntity<List<UserAdressDto>> getUserAdress(@PathVariable Long userId)
    {
       List<UserAdressDto> userAdress= userAdressService.getUserAdress(userId);
       return new ResponseEntity<>(userAdress,HttpStatus.OK);
    }
}
