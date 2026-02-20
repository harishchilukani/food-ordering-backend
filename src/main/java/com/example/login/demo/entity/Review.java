package com.example.login.demo.entity;

import ch.qos.logback.core.pattern.util.RestrictedEscapeUtil;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Columns;

import java.time.LocalDateTime;

@Entity
@Data
public class Review {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private int rating;
    private String comment;
    private LocalDateTime reviewdAt;
    @ManyToOne
    @JoinColumn(name="user_id",nullable = true)
    private User user;
    @ManyToOne
    @JoinColumn(name="menu_item_id",nullable = true)
    private MenuItem menuitem;
    @ManyToOne
    @JoinColumn(name="restaurant_id",nullable=true)
    private RestaurantEntity restaurantEntity;
}
