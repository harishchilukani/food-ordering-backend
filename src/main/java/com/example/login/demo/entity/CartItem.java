package com.example.login.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    @JsonBackReference

    private User user;
    @ManyToOne
    @JoinColumn(name="menu_item_id",nullable = false)


    private MenuItem menuItem;

    private int quantity;
    private Double price;


}
