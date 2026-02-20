package com.example.login.demo.repository;

import com.example.login.demo.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface RestaurantRepository extends JpaRepository<RestaurantEntity,Long> {
    @Query(value = "SELECT r.id,r.restaurantname,(6371 * acos( cos(radians(:latitude)) * cos(radians(latitude)) * cos(radians(longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(latitude)))) AS distance " +
            "FROM restaurant_entity r " +
            "HAVING distance < :radius " +
            "ORDER BY distance",
            nativeQuery = true)
    List<Object[]> findNearByRestaurants(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("radius") double radius);



}
