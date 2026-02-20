package com.example.login.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAdressDto {
    private String street;
    private String city;
    private double latitude;
    private double longitude;
}
