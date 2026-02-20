package com.example.login.demo.service;

import com.example.login.demo.entity.OrderSummary;
import org.springframework.stereotype.Service;

@Service
public interface OrderSummaryService {
    OrderSummary adressDetailsForDeliveryPerson(Long orderId);

}
