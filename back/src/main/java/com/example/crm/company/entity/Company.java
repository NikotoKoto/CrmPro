package com.example.crm.company.entity;

import com.example.crm.contact.entity.Contact;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="companies")
public class Company {

    public Company(){}

    public Company(String name){
        this.name = name;
    }

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
