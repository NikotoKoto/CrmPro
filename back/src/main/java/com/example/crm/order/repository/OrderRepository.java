package com.example.crm.order.repository;

import com.example.crm.order.entity.Order;
import com.example.crm.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
