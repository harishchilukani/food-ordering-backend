package com.example.login.demo.controller;

import com.example.login.demo.entity.Review;
import com.example.login.demo.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequiredArgsConstructor

public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/restaurant/review/{userId}/{restaurantId}")
    public ResponseEntity<String> postReviewRestaurant(@PathVariable Long userId, @PathVariable Long restaurantId, @RequestBody Review review )
    {
        reviewService.postReviewRestaurant(userId,restaurantId,review);
        return new ResponseEntity<>("review added succsessfully",HttpStatus.OK);

    }
    @PostMapping("/menuitem/review/{userId}/{menuItemId}")
    public  ResponseEntity<String > postReviewMenuItem(@PathVariable Long  userId,@PathVariable Long  menuItemId,@RequestBody Review review)
    {
        reviewService.postReviewMenuItem(userId,menuItemId,review);
        return new ResponseEntity<>("review added successfully",HttpStatus.OK);
    }
    //for getting all reviews for particular menu item
    @GetMapping("/menuitem/getreview/{menuItemId}")
    public ResponseEntity<List<Review>> getMenuItemReview(@PathVariable(name="menuItemId") Long menuItemId)
    {
       List<Review> menuItemReview= reviewService.getMenuItemReview(menuItemId);
        return new ResponseEntity<>(menuItemReview,HttpStatus.OK);
    }
    //for getting all reviews for a restuarant
    @GetMapping("/restaurant/getreview/{restaurantId}")
    public ResponseEntity<List<Review>> getRestaurantReview(@PathVariable Long restaurantId)
    {
        List<Review> restaurantReview= reviewService.getRestaurantReview(restaurantId);
        return new ResponseEntity<>(restaurantReview,HttpStatus.OK);
    }
    // getting review  of user given to particular restaurant
    @GetMapping("user/restaurant/getreview/{userId}/{restaurantId}")
    public ResponseEntity<Review> reviewPostedByUserToRestaurant(@PathVariable Long userId,@PathVariable Long restaurantId)
    {
        Review reviewByUserToRestaurant =reviewService.getreviewByuserToRestaurant(userId,restaurantId);
        return new ResponseEntity<>(reviewByUserToRestaurant,HttpStatus.OK);
    }
    @GetMapping("user/menuitem/getreview/{userId}/{menuItemId}")
    public ResponseEntity<Review> reviewPostedByUserToMenuItem(@PathVariable Long userId,@PathVariable Long menuItemId)
    {
        Review reviewByUserToMenuItem =reviewService.getreviewByuserToMenuItem(userId,menuItemId);
        return new ResponseEntity<>(reviewByUserToMenuItem,HttpStatus.OK);
    }
    @GetMapping("averagerating/restaurant/{restaurantId}")
    public ResponseEntity<Double> averageRatingForRestaurant(@PathVariable Long restaurantId){
            Double avgrating=reviewService.getAverageRatingForRestaurant(restaurantId);
            return new ResponseEntity<>(avgrating,HttpStatus.OK);


    }
    @GetMapping("averagerating/menuitem/{menuItemId}")
    public ResponseEntity<Double> averageRatingFormenuItem(@PathVariable Long menuItemId){
        Double avgrating=reviewService.getAverageRatingForMenuItem(menuItemId);
        return new ResponseEntity<>(avgrating,HttpStatus.OK);


    }



}
