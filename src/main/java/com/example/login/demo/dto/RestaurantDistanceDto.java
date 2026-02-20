package com.example.login.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDistanceDto {
    private Long id;
    private String restaurantname;
    private double distance;
}
