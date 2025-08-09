package com.example.crm.order.repository;

import com.example.crm.order.entity.Order;
import com.example.crm.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Liste uniquement les commandes du user courant
    List<Order> findByUserId(Long userId);

    // Lit/MAJ/Supprime une commande uniquement si elle appartient au user courant
    Optional<Order> findByIdAndUserId(Long id, Long userId);
}
