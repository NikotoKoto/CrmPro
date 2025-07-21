package com.example.crm.Order.controller;

import com.example.crm.Order.dto.CreateOrderRequestDto;
import com.example.crm.Order.dto.OrderResponseDto;
import com.example.crm.Order.service.OrderService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody CreateOrderRequestDto dto,
                                                        @AuthenticationPrincipal UserDetails userDetails){
        System.out.println("UserDetails username: " + userDetails.getUsername());
        OrderResponseDto response = orderService.createOrder(dto,userDetails.getUsername());
        return ResponseEntity.ok(response);

    }

    @GetMapping
    public ResponseEntity <List<OrderResponseDto>> getOrder(@AuthenticationPrincipal UserDetails userDetails){
        List<OrderResponseDto> order = orderService.getOrderByUser(userDetails.getUsername());
        return ResponseEntity.ok(order);
    }
}
