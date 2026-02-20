package com.example.login.demo.repository;

import com.example.login.demo.entity.OrderSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderSummaryRepository extends JpaRepository<OrderSummary, Long>
{
    Optional<OrderSummary> findByOrderId(Long orderId);
}
