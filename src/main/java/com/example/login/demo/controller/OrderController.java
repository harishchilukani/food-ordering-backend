package com.example.login.demo.controller;

import com.example.login.demo.dto.PlaceOrderDto;
import com.example.login.demo.dto.UserAdressDto;
import com.example.login.demo.entity.Order;
import com.example.login.demo.service.MenuService;
import com.example.login.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping("placeorder/{restauarantId}/{userId}")
    public ResponseEntity<PlaceOrderDto> placeOrder(@PathVariable Long restauarantId,@PathVariable long userId, @RequestBody UserAdressDto useradress)
    {
           PlaceOrderDto orderplaced=orderService.placeOrder(restauarantId,userId,useradress);
           return new ResponseEntity<>(orderplaced, HttpStatus.OK);
    }
    @GetMapping("getallorders/{userId}")
    public ResponseEntity<List<PlaceOrderDto>> getAllOrders(@PathVariable long userId)
    {
        List<PlaceOrderDto> allOrders=orderService.getAllOrders(userId);
        return new ResponseEntity<>(allOrders,HttpStatus.OK);
    }
    @GetMapping("getorder/{orderId}")
    public ResponseEntity<PlaceOrderDto> getOrder(@PathVariable long orderId)
    {
        PlaceOrderDto getOrder=orderService.getOrder(orderId);
        return new ResponseEntity<>(getOrder,HttpStatus.OK);
    }





}
