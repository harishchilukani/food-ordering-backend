package com.example.login.demo.dto;

import com.example.login.demo.entity.UserAdress;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PlaceOrderDto {

    private Long orderId;
    private String userName;
    private Double totalAmount;
    private String status;
    private LocalDateTime orderAt;
    private String restaurant;
    private String restaurantLocation;
    private double restaurantLongitude;
    private double restaurantLatitude;

    private String userStreet;
    private String userCity;
    private double userLatitude;
    private double userLongitude;


    private List<OrderItemDto> items;


}
