package com.example.login.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="orders")
@Data
public class Order {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; 
    private Double totalAmount;
    private  String status;
    private LocalDateTime orderTime;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;
    @ManyToOne
    @JoinColumn(name="restaurant_id",nullable=false)
    @JsonBackReference
    private RestaurantEntity restaurantEntity;




}
