package com.example.crm.Order.entity;

import com.example.crm.Company.entity.Company;
import com.example.crm.Order.enums.OrderStatus;
import com.example.crm.User.entity.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name="order_date",nullable = false)
    private LocalDate orderDate = LocalDate.now();

    @Column(name="status",nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    @Column(name="orders",nullable = false)
    private String orders;

    @Column(name="total_amount",nullable = false)
    private BigDecimal totalAmount;


@ManyToOne(optional = false)
@JoinColumn(name="user_id", nullable = false)
private User user;

//Getter & setter

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public LocalDate getOrderDate(){
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate ){
        this.orderDate = orderDate;

    }

    public void setOrders (String orders){
        this.orders = orders;
    }
    public String getOrders(){
        return orders;
    }

    public OrderStatus getOrderStatus(){
        return status;
    }

    public void setOrderStatus(OrderStatus status){
        this.status = status;
    }
    public BigDecimal getTotalAmount(){
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount){
        this.totalAmount = totalAmount;
    }

    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }
}

