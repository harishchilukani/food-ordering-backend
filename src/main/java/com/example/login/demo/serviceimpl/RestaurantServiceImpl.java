package com.example.login.demo.serviceimpl;

import com.example.login.demo.dto.RestaurantDistanceDto;
import com.example.login.demo.entity.RestaurantEntity;
import com.example.login.demo.exception.ResourceNotFoundException;
import com.example.login.demo.repository.RestaurantRepository;
import com.example.login.demo.service.RestaurantService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.management.relation.RelationService;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    @Override
    public String addRestaurant(RestaurantEntity restaurantEntity) {
        restaurantRepository.save(restaurantEntity);
        return "restaurant added successfully";
    }

    @Override
    public List<RestaurantEntity> allRestaurant() {
        List<RestaurantEntity> restaurants=restaurantRepository.findAll();
        return restaurants;
    }

    @Override
    public RestaurantEntity updateRestaurant(Long id,RestaurantEntity restaurantEntity) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Restaurant ID must be positive");
        }
           RestaurantEntity restaurant=restaurantRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Restaurant not found :" + id));
             restaurant.setDescription(restaurantEntity.getDescription());
             restaurant.setLocation(restaurantEntity.getLocation());

             restaurant.setRestaurantname(restaurantEntity.getRestaurantname());
             restaurantRepository.save(restaurant);
             return restaurant;
    }

    @Override
    public String deleteRestaurant(Long id) {
        restaurantRepository.findById(id).orElseThrow(()->new RuntimeException("restaurant not found :" + id));
        restaurantRepository.deleteById(id);
        return "restuarant deleted successfully";

    }

    @Override
    public Page<RestaurantEntity> sortingOrder(int page,int size,String sortFiled,String order) {
        Sort sort=order.equalsIgnoreCase("desc") ?
                Sort.by(Sort.Direction.DESC,"avgrating"):
                Sort.by(Sort.Direction.ASC,"avgrating");
        Pageable pageable= PageRequest.of(page,size,sort);
        return restaurantRepository.findAll(pageable);
    }

    @Override
    public List<RestaurantDistanceDto > findNearestDistanceRestauarnts(double latitude, double longitude, double radius) {
           List<Object[]> result=restaurantRepository.findNearByRestaurants(latitude,longitude,radius);
           List<RestaurantDistanceDto> nearby=result.stream().map(obj-> new RestaurantDistanceDto(((Number)obj[0]).longValue(),(String)obj[1],((Number)obj[2]).doubleValue()))
                .collect(Collectors.toList());
           return nearby;
    }


}
