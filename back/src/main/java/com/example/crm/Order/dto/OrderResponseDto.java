package com.example.crm.Order.dto;

import com.example.crm.Company.entity.Company;
import com.example.crm.Order.entity.Order;
import com.example.crm.Order.enums.OrderStatus;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderResponseDto {
    private Long id;
    private String companyName;
    private LocalDate orderDate;
    private OrderStatus orderStatus;
    private BigDecimal totalAmount;


    //Getter & setter

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getCompanyName(){
        return companyName;
    }

    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    public LocalDate getOrderDate(){
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate ){
        this.orderDate = orderDate;

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
