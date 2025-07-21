package com.example.crm.Contact.dto;

import com.example.crm.Company.entity.Company;

public class CreateContactRequestDto {

    private String name;
    private String phone;
    private String email;
    private String company;


    //Getter & setter email
    public String getEmail (){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    //Getter & setter name
    public String getName (){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    //Getter & setter phone
    public String getPhone (){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    //Getter & setter company
    public String getCompany (){
        return company;
    }

    public void setCompany(String company){
        this.company = company;
    }

    @Override
    public String toString() {
        return "CreateContactRequestDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
