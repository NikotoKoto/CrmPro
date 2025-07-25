package com.example.crm.Order.dto;

import com.example.crm.Company.entity.Company;
import com.example.crm.Order.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UpdateOrderRequestDto {
    private String company;
    private LocalDate orderDate;
    private OrderStatus orderStatus;
    private String orders;
    private BigDecimal totalAmount;


    //Getter & setter



    public String getCompany(){
        return company;
    }

    public void setCompany(String companyName){
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

    public void setCompany(BigDecimal totalAmount){
        this.totalAmount = totalAmount;
    }
}
