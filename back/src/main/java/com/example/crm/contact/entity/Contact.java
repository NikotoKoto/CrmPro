package com.example.crm.contact.entity;

import com.example.crm.company.entity.Company;
import com.example.crm.User.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name= "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;
    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public Long getId (){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }


    //Getter & setter owner
    public User getOwner(){
        return owner;
    }

    public void setOwner(User owner){
        this.owner = owner;
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
    public Company getCompany (){
        return company;
    }

    public void setCompany(Company company){
        this.company = company;
    }

}
