package com.example.login.demo.repository;

import com.example.login.demo.entity.CartItem;
import com.example.login.demo.entity.Order;
import com.example.login.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUserOrderByOrderTimeDesc(User user);
}
