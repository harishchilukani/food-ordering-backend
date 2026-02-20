package com.example.login.demo.serviceimpl;

import com.example.login.demo.dto.UserAdressDto;
import com.example.login.demo.entity.User;
import com.example.login.demo.entity.UserAdress;
import com.example.login.demo.exception.ResourceNotFoundException;
import com.example.login.demo.repository.UserAdressRepository;
import com.example.login.demo.repository.UserRepository;
import com.example.login.demo.service.UserAdressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAdressServiceImp implements UserAdressService {
    private final UserAdressRepository userAdressRepository;
    private final UserRepository userRepository;
    @Override
    public void saveUserAdress(Long userId, UserAdress userAdress) {
        User user=userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user not found"));

        UserAdress adress=new UserAdress();
        adress.setUser(user);
        adress.setCity(userAdress.getCity());
        adress.setStreet(userAdress.getStreet());
        adress.setLatitude(userAdress.getLatitude());
        adress.setLongitude(userAdress.getLongitude());
        userAdressRepository.save(adress);



    }
    public List<UserAdressDto> getUserAdress(Long userId){
        List<UserAdress> useradress=userAdressRepository.findByUserId(userId);
        List<UserAdressDto> useralladresses=new ArrayList<>();
        for(UserAdress i:useradress)
        {
            UserAdressDto useradressdto=new UserAdressDto();
            useradressdto.setCity(i.getCity());
            useradressdto.setLatitude(i.getLatitude());
            useradressdto.setLongitude(i.getLongitude());
            useradressdto.setStreet(i.getStreet());
            useralladresses.add(useradressdto);
        }
        return useralladresses;


    }
}
