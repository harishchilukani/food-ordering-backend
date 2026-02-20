package com.example.login.demo.repository;

import com.example.login.demo.entity.RestaurantEntity;
import com.example.login.demo.entity.Review;
import com.example.login.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findByMenuitemId(Long menuItemId);
    List<Review > findByRestaurantEntityId(Long restaurantId );
   Optional <Review> findByUserIdAndRestaurantEntityId(Long userId,Long restaurantId);
    Optional <Review> findByUserIdAndMenuitemId(Long userId,Long restaurantId);

}
