package com.example.login.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderItem {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String itemname;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    private double price;
}
