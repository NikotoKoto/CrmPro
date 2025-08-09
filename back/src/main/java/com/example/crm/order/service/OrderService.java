package com.example.crm.order.service;

import com.example.crm.company.repository.CompanyRepository;
import com.example.crm.company.entity.Company;
import com.example.crm.order.dto.CreateOrderRequestDto;
import com.example.crm.order.dto.OrderResponseDto;
import com.example.crm.order.dto.UpdateOrderRequestDto;
import com.example.crm.order.entity.Order;
import com.example.crm.order.mapper.OrderMapper;
import com.example.crm.order.repository.OrderRepository;
import com.example.crm.User.entity.User;
import com.example.crm.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        CompanyRepository companyRepository,
                        UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    public OrderResponseDto createOrder(CreateOrderRequestDto dto, String email){
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Company company = companyRepository.findByName(dto.getCompany().getName())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        Order order = OrderMapper.toEntity(dto, company);
        order.setUser(user); // 👈 ownership forcée
        return OrderMapper.toDto(orderRepository.save(order));
    }

    public List<OrderResponseDto> getOrderByUser(String email){
        Long uid = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();

        return orderRepository.findByUserId(uid)
                .stream()
                .map(OrderMapper::toDto)
                .toList();
    }

    public OrderResponseDto getOrderById(Long id, String email){
        Long uid = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();

        Order order = orderRepository.findByIdAndUserId(id, uid)
                .orElseThrow(() -> new RuntimeException("Order not found or not yours"));

        return OrderMapper.toDto(order);
    }

    public OrderResponseDto updateOrder(Long id, String email, UpdateOrderRequestDto dto){
        Long uid = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();

        Company company = companyRepository.findByName(dto.getCompany().getName())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        // 👇 sécurise l'accès : uniquement si la commande appartient à l'utilisateur
        Order order = orderRepository.findByIdAndUserId(id, uid)
                .orElseThrow(() -> new RuntimeException("Order not found or not yours"));

        Order updated = OrderMapper.toEntity(dto, company, order); // ne pas toucher order.setUser
        return OrderMapper.toDto(orderRepository.save(updated));
    }

    public void DeleteOrder(Long id, String email){
        Long uid = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();

        // 👇 même règle : ne supprime que si c'est à moi
        Order order = orderRepository.findByIdAndUserId(id, uid)
                .orElseThrow(() -> new RuntimeException("Order not found or not yours"));

        orderRepository.delete(order);
    }
}

