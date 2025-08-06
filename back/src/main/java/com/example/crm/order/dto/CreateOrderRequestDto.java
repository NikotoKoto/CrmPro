package com.example.crm.order.dto;

import com.example.crm.company.entity.Company;
import com.example.crm.order.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateOrderRequestDto {
    private Company company;
    private LocalDate orderDate;
    private OrderStatus orderStatus;
    private String orders;
    private BigDecimal totalAmount;


    //Getter & setter



    public Company getCompany(){
        return company;
    }
    public void setCompany  (Company company){
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
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus){
        this.orderStatus = orderStatus;
    }
    public BigDecimal getTotalAmount(){
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount){
        this.totalAmount = totalAmount;
    }
}
