package com.example.login.demo.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private String itemName;
    private Double price;
    private int quantity;
}
