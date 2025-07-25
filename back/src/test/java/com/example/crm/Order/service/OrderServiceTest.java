package com.example.crm.Order.service;

import com.example.crm.Company.entity.Company;
import com.example.crm.Company.repository.CompanyRepository;
import com.example.crm.Contact.repository.ContactRepository;
import com.example.crm.Order.dto.CreateOrderRequestDto;
import com.example.crm.Order.dto.OrderResponseDto;
import com.example.crm.Order.entity.Order;
import com.example.crm.Order.enums.OrderStatus;
import com.example.crm.Order.repository.OrderRepository;
import com.example.crm.User.entity.User;
import com.example.crm.User.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void ShouldCreateOrderSuccessFully(){
        /*Arrange*/
        User user = new User();

        user.setId(1L);
        user.setEmail("Toto@gmail.com");

        Company company = new Company("Lacoste");

        CreateOrderRequestDto createOrder = new CreateOrderRequestDto();
        createOrder.setOrders("Voiture electrique");
        createOrder.setCompany("Lacoste");
        createOrder.setOrderStatus(OrderStatus.PENDING);
        createOrder.setOrderDate(LocalDate.now());
        createOrder.setTotalAmount(BigDecimal.valueOf(25000));

        Order savedOrder = new Order();

        savedOrder.setOrderStatus(OrderStatus.PENDING);
        savedOrder.setOrderDate(LocalDate.now());
        savedOrder.setOrders("Voiture electrique");
        savedOrder.setTotalAmount(BigDecimal.valueOf(25000));
        savedOrder.setCompany(company);

        when(companyRepository.findByName("Lacoste")).thenReturn(Optional.of(company));
        when(userRepository.findByEmailIgnoreCase("Toto@gmail.com")).thenReturn(Optional.of(user));
        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        /*Act*/

        OrderResponseDto result = orderService.createOrder(createOrder,"Toto@gmail.com");

        /*Assert*/

        assertNotNull(result);
        assertEquals("Voiture electrique", result.getOrders());
        assertEquals(OrderStatus.PENDING, result.getOrderStatus());

    }
}
