package com.example.login.demo.repository;

import com.example.login.demo.entity.MenuItem;
import com.example.login.demo.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<MenuItem,Long> {
    Optional<List<MenuItem>> findByRestaurantEntity(RestaurantEntity Restaurant);

}
