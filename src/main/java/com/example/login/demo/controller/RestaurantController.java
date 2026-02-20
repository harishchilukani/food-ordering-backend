package com.example.login.demo.controller;

import com.example.login.demo.dto.RestaurantDistanceDto;
import com.example.login.demo.entity.RestaurantEntity;
import com.example.login.demo.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class RestaurantController {

    private final RestaurantService restaurantService;


    @PostMapping("/addrestaurant")
    public ResponseEntity<String> addRestaurant(@Valid @RequestBody  RestaurantEntity restaurantEntity)
    {
         String str=restaurantService.addRestaurant(restaurantEntity);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping("/allrestaurant")
    public ResponseEntity<List<RestaurantEntity>> allRestaurant(){
        List<RestaurantEntity> restuarants=restaurantService.allRestaurant();
        return new ResponseEntity<>(restuarants,HttpStatus.OK);
    }

    @PutMapping("/a/{id}")
    public ResponseEntity<RestaurantEntity> updateRestaurant(@PathVariable Long id,@RequestBody  RestaurantEntity restaurantEntity)
    {
          RestaurantEntity updatedRestaurant= restaurantService.updateRestaurant(id,restaurantEntity);
          return new ResponseEntity<>(updatedRestaurant,HttpStatus.OK);
    }
    @DeleteMapping("deleterestaurant/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Long id)
    {
           String str=restaurantService.deleteRestaurant(id);
           return new ResponseEntity<>(str,HttpStatus.OK);
    }
    @GetMapping("/sortrestaurant/{page}/{size}/{sortFiled}/{order}")
    public Page<RestaurantEntity> sorting(@PathVariable int page, @PathVariable int size, @PathVariable String sortFiled, @PathVariable String order)
    {
        return restaurantService.sortingOrder(page,size,sortFiled,order);

    }
    //finding the distance of restaurant from user distance
    @GetMapping("findnearRestaurants/{latitude}/{longitude}/{radius}")
    public ResponseEntity<List<RestaurantDistanceDto >> findNearestDistanceRestauarnts(@PathVariable double latitude,@PathVariable double longitude,@PathVariable double radius)
    {
        List<RestaurantDistanceDto> nearRestaurants=restaurantService.findNearestDistanceRestauarnts(latitude,longitude,radius);
        return new ResponseEntity<>(nearRestaurants,HttpStatus.OK);
    }


}
