package com.example.crm.User.entity;

import com.example.crm.Contact.entity.Contact;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name= "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String password;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> contacts;

    //Getter & setter id

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

    //Getter & setter password
    public String getPassword (){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
