package com.example.crm.Order.mapper;


import com.example.crm.Company.entity.Company;
import com.example.crm.Order.dto.CreateOrderRequestDto;
import com.example.crm.Order.dto.OrderResponseDto;
import com.example.crm.Order.dto.UpdateOrderRequestDto;
import com.example.crm.Order.entity.Order;


import java.time.LocalDate;

public class OrderMapper {

    public static Order toEntity(CreateOrderRequestDto dto, Company company){
        Order order = new Order();
        order.setCompany(company);
        order.setOrderDate(dto.getOrderDate());
        order.setOrderStatus(dto.getOrderStatus());
        order.setOrders(dto.getOrders());
        order.setTotalAmount(dto.getTotalAmount());
        return order;
    }
    public static Order toEntity(UpdateOrderRequestDto dto, Company company, Order order){
        order.setCompany(company);
        order.setOrderDate(dto.getOrderDate());
        order.setOrderStatus(dto.getOrderStatus());
        order.setOrders(dto.getOrders());
        order.setTotalAmount(dto.getTotalAmount());
        return order;
    }
    public static OrderResponseDto toDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setOrders(order.getOrders());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setCompany(order.getCompany().getName());
        return dto;
    }
}
