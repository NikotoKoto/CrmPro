package com.example.crm.Contact.dto;

public class ContactResponseDto {
    private Long id;
    private String email;
    private String name;
    private String phone;
    private String company;


    public Long getId (){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

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
}
