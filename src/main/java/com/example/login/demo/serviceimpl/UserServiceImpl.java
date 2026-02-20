package com.example.login.demo.serviceimpl;

import com.example.login.demo.config.JwtService;
import com.example.login.demo.dto.UserDto;
import com.example.login.demo.entity.Role;
import com.example.login.demo.entity.User;
import com.example.login.demo.repository.UserRepository;
import com.example.login.demo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Override
    public String signUp(User user) {
      Optional<User> presentuser = userRepository.findByUsername(user.getUsername());
        if(presentuser.isPresent()) {
            return "User is present in the database";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String email = user.getEmail().toLowerCase();
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);

        if (email.endsWith("@company.com")) {
            roles.add(Role.ADMIN);
        } else if (email.endsWith("@manager.com")) {
            roles.add(Role.MANAGER);
        }

        user.setRoles(roles);
        userRepository.save(user);
        return "signup sucessfully";
    }

    @Override
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("user not found"));

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch(BadCredentialsException ex){
            throw new RuntimeException();
        }
        // Assuming user.getId() returns the user ID

        return jwtService.generateToken(user);
       // return jwtService.generateToken(user);
    }

    @Override
    public UserDto gettingDetails(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        UserDto profile=new UserDto();
        profile.setName(user.getName());
        profile.setEmail(user.getEmail());
        profile.setNumber(user.getNumber());
        profile.setUsername(user.getUsername());
        return profile;
    }
}
