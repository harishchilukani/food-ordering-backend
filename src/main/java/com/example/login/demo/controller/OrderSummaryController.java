package com.example.login.demo.controller;

import com.example.login.demo.entity.OrderSummary;
import com.example.login.demo.entity.RestaurantEntity;
import com.example.login.demo.service.OrderSummaryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderSummaryController {
    private final OrderSummaryService orderSummaryService;

    private final static Logger logger = LoggerFactory.getLogger(OrderSummaryController.class);
    @GetMapping("/adressdetails/{orderId}")
    public ResponseEntity<OrderSummary> adressDetailsForDeliveryPerson(@PathVariable(name = "orderId") Long orderId)
    {
       // logger.info("{}", orderId);
        OrderSummary adressDetails=orderSummaryService.adressDetailsForDeliveryPerson(orderId);
        return new ResponseEntity<>(adressDetails, HttpStatus.OK);

    }
}
