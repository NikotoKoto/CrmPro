package com.example.crm.Order.service;

import com.example.crm.Company.controller.CompanyRepository;
import com.example.crm.Company.entity.Company;
import com.example.crm.Order.dto.CreateOrderRequestDto;
import com.example.crm.Order.dto.OrderResponseDto;
import com.example.crm.Order.entity.Order;
import com.example.crm.Order.mapper.OrderMapper;
import com.example.crm.Order.repository.OrderRepository;
import com.example.crm.User.entity.User;
import com.example.crm.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private CompanyRepository companyRepository;

    @Autowired
    public OrderService( OrderRepository orderRepository, CompanyRepository companyRepository, UserRepository userRepository){
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    public OrderResponseDto createOrder(CreateOrderRequestDto dto, String email){
        System.out.println("email>>>>>>>>>" + email);
        User user =  userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(()-> new RuntimeException("User not found"));
        Company company = companyRepository.findByName(dto.getCompany())
                .orElseThrow(()-> new RuntimeException("Company not found"));
        Order order = OrderMapper.toEntity(dto, company);
        order.setUser(user);
        Order orderSaved = orderRepository.save(order);
        return OrderMapper.toDto(orderSaved);

    }

    public List<OrderResponseDto> getOrderByUser (String email){
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(()-> new RuntimeException("User not found"));
        return orderRepository.findByUser(user)
                .stream()
                .map(OrderMapper :: toDto)
                .toList();
    }

}
