package com.example.login.demo.service;

import com.example.login.demo.entity.RestaurantEntity;
import com.example.login.demo.entity.Review;
import com.example.login.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
   public void postReviewRestaurant(Long userId, Long  restaurantId, Review review);
   public void postReviewMenuItem(Long  userId,Long  menuItemId, Review review);
   public List<Review> getMenuItemReview(Long menuItemId);
   public List<Review> getRestaurantReview(Long restaurantId);
   public Review getreviewByuserToRestaurant(Long userId,Long restaurantId);
   public Review getreviewByuserToMenuItem(Long userId, Long menuItemId);
   public Double getAverageRatingForRestaurant(Long restaurantId);
  public Double getAverageRatingForMenuItem(Long menuItemId);
}
