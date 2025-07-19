package com.example.crm.Company.entity;

import com.example.crm.Contact.entity.Contact;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "company")
    private List<Contact> contacts;



    //getter & setter

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

}
