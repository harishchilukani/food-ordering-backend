package com.example.login.demo.serviceimpl;

import com.example.login.demo.entity.OrderSummary;
import com.example.login.demo.repository.OrderSummaryRepository;
import com.example.login.demo.service.OrderSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderSummaryServiceImp implements OrderSummaryService {
    private final OrderSummaryRepository orderSummaryRepository;

    @Override
    public OrderSummary adressDetailsForDeliveryPerson(Long orderId) {
        OrderSummary adressDetails=orderSummaryRepository.findByOrderId(orderId).orElseThrow(() -> new RuntimeException());
        return adressDetails;
    }
}
