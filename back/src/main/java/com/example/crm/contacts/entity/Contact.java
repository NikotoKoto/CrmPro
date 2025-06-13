package com.example.crm.contacts.entity;

import com.example.crm.user.entity.UserEntity;
import jakarta.persistence.*;

@Entity
public class Contact {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private String firstname;
    private String email;
    private String phone;


    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UserEntity getUser() {
        return user;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname(){
        return firstname;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPhone(String phone){this.phone = phone;}

    public String getPhone(){return phone;}
}
