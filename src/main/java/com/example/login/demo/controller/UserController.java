package com.example.login.demo.controller;

import com.example.login.demo.dto.UserDto;
import com.example.login.demo.entity.User;
import com.example.login.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user){
       String str= userService.signUp(user);



            return new ResponseEntity<>(str, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password){

        String token = userService.login(username, password);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/gettinguserdetails/{userId}")
    public ResponseEntity<UserDto> gettingDetails(@PathVariable Long userId)
    {
        UserDto profile=userService.gettingDetails(userId);
        return new ResponseEntity<>(profile,HttpStatus.OK);

    }


    @GetMapping("/request")
    public ResponseEntity<String> add(){
        return new ResponseEntity<>("yes request is working", HttpStatus.OK);
    }

}
