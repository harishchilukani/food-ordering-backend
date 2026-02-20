package com.example.login.demo.service;

import com.example.login.demo.dto.PlaceOrderDto;
import com.example.login.demo.dto.UserAdressDto;
import com.example.login.demo.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface OrderService {
    public PlaceOrderDto placeOrder(Long restauarantId,Long userId, UserAdressDto useradress);
    public List< PlaceOrderDto> getAllOrders(Long userId);
    public PlaceOrderDto getOrder(Long orderId);
}
