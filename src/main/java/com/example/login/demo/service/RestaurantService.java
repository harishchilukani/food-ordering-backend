package com.example.login.demo.service;

import com.example.login.demo.dto.RestaurantDistanceDto;
import com.example.login.demo.entity.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestaurantService {
    String addRestaurant(RestaurantEntity restaurantEntity);
    List<RestaurantEntity> allRestaurant();
     RestaurantEntity updateRestaurant(Long id, RestaurantEntity restaurantEntity);
    String  deleteRestaurant(Long id);

     Page<RestaurantEntity> sortingOrder(int page, int size, String sortFiled, String order);
    List<RestaurantDistanceDto> findNearestDistanceRestauarnts(double latitude, double longitude, double radius);

}
