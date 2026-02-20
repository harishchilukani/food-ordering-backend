package com.example.login.demo.entity;

import com.example.login.demo.dto.OrderItemDto;
import com.example.login.demo.dto.UserAdressDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSummary {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private Long orderId;
    //restauarant details
    private String restaurant;
    private String restaurantLocation;
    private double restaurantLongitude;
    private double restaurantLatitude;
    //useradressdetails
    private String userStreet;
    private String userCity;
    private double userLatitude;
    private double userLongitude;




    //private List<OrderItemDto> items;
}
