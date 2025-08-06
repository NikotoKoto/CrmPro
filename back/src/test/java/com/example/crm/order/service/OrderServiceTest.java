package com.example.crm.order.service;

import com.example.crm.company.entity.Company;
import com.example.crm.company.repository.CompanyRepository;
import com.example.crm.contact.repository.ContactRepository;
import com.example.crm.order.dto.CreateOrderRequestDto;
import com.example.crm.order.dto.OrderResponseDto;
import com.example.crm.order.entity.Order;
import com.example.crm.order.enums.OrderStatus;
import com.example.crm.order.repository.OrderRepository;
import com.example.crm.User.entity.User;
import com.example.crm.User.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
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
        createOrder.setCompany(company);
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

    @Test
    void ShouldGetOrderByIdByUserIdSuccessFully(){
        /*Arrange*/
        User user = new User();
        user.setId(1L);
        user.setEmail("Toto@gmail.com");

        Company company1 = new Company("Lacoste");

        Order order1 = new Order();
        order1.setId(1L);
        order1.setOrders("Voiture electrique");
        order1.setCompany(company1);
        order1.setOrderStatus(OrderStatus.PENDING);
        order1.setOrderDate(LocalDate.now());
        order1.setTotalAmount(BigDecimal.valueOf(25000));
        order1.setUser(user);

        when(userRepository.findByEmailIgnoreCase("Toto@gmail.com")).thenReturn(Optional.of(user));
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order1));

      /*Act*/
        OrderResponseDto result = orderService.getOrderById(order1.getId(),"Toto@gmail.com");

      /*Assert*/
      assertNotNull(result);
      assertEquals("Voiture electrique", result.getOrders());
      assertEquals(OrderStatus.PENDING, result.getOrderStatus());
      assertEquals(LocalDate.now(), result.getOrderDate());
      assertNotEquals("Voiture thermique", result.getOrders());
    }

    @Test
    void ShouldGetOrderByUserSuccessFully(){
        /*Arrange*/
        User user = new User();
        user.setId(1L);
        user.setEmail("Toto@gmail.com");

        Company company1 = new Company("Lacoste");
        Company company2 = new Company("Shift");
        Order order1 = new Order();
        order1.setId(1L);
        order1.setOrders("Voiture thermique");
        order1.setCompany(company1);
        order1.setOrderStatus(OrderStatus.PENDING);
        order1.setOrderDate(LocalDate.now());
        order1.setTotalAmount(BigDecimal.valueOf(25000));
        order1.setUser(user);

        Order order2 = new Order();
        order2.setId(1L);
        order2.setOrders("Voiture electrique");
        order2.setCompany(company2);
        order2.setOrderStatus(OrderStatus.DELIVERED);
        order2.setOrderDate(LocalDate.now());
        order2.setTotalAmount(BigDecimal.valueOf(1500));
        order2.setUser(user);

        List<Order> mockOrders = List.of(order1,order2);

        when(userRepository.findByEmailIgnoreCase("Toto@gmail.com")).thenReturn(Optional.of(user));
        when(orderRepository.findByUser(user)).thenReturn(mockOrders);

        /*Act*/
        List<OrderResponseDto> result = orderService.getOrderByUser("Toto@gmail.com");

        /*Assert*/
        assertEquals("Lacoste",result.get(0).getCompany());
        assertEquals("Shift",result.get(1).getCompany());
        assertEquals(order1.getOrderStatus(),result.get(0).getOrderStatus());
        assertEquals(order2.getOrderStatus(),result.get(1).getOrderStatus());
    }

    @Test
    void ShouldDeleteOrderSuccessFully(){
        /*Arange*/
        User user = new User();
        user.setId(1L);
        user.setEmail("Toto@gmail.com");

        Company company1 = new Company("Lacoste");


        Order order1 = new Order();
        order1.setId(1L);
        order1.setOrders("Voiture thermique");
        order1.setCompany(company1);
        order1.setOrderStatus(OrderStatus.PENDING);
        order1.setOrderDate(LocalDate.now());
        order1.setTotalAmount(BigDecimal.valueOf(25000));
        order1.setUser(user);

        when(userRepository.findByEmailIgnoreCase("Toto@gmail.com")).thenReturn(Optional.of(user));
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order1));

        /*Act*/
        orderService.DeleteOrder(1L,"Toto@gmail.com");

        /*Assert*/
        verify(orderRepository).delete(order1);
    }
    @Test
    void ShouldThrowExceptionWhenDeletingOrderNotOwnedByUser() {
        // Arrange
        User realUser = new User(); realUser.setEmail("Toto@gmail.com");
        User anotherUser = new User(); anotherUser.setEmail("bad@gmail.com");
        Company company = new Company("Lacoste");

        Order order = new Order();
        order.setUser(anotherUser);
        order.setId(1L);
        order.setCompany(company);
        order.setOrders("Voiture");
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());
        order.setTotalAmount(BigDecimal.valueOf(1000));

        when(userRepository.findByEmailIgnoreCase("Toto@gmail.com")).thenReturn(Optional.of(realUser));
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        // Act + Assert
        Exception ex = assertThrows(RuntimeException.class, () -> {
            orderService.DeleteOrder(1L, "Toto@gmail.com");
        });

        assertEquals("Unauthorized", ex.getMessage());
    }
}
